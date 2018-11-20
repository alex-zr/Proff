package com.gmail.vladgural;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class MyController {

    private Map<Long, byte[]> photos = new HashMap<>();
    private Map<Long, String> fileName = new HashMap<>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();
        //ZiInputStream
        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());
            fileName.put(id, photo.getOriginalFilename());

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping(value = "/view_all_photo")
    public String onShowAllPhotos(Model model) {
        model.addAttribute("map", photos);
        return "allphotos";
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/download_zip", method = RequestMethod.POST)
    public void onDownloadZip(@RequestParam("photo_id") long id, HttpServletResponse resp) {
        String originFileName = fileName.get(id);
        String dir = "C:\\Temp\\Java\\LadWorks\\Proff\\vlad_gural_wab_spring_mvc\\src\\main\\webapp\\WEB-INF\\pages\\";
        File file = new File(dir + Long.toString(id) + ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(
                new FileOutputStream(file))) {
            ZipEntry entry = new ZipEntry(originFileName);
            zos.putNextEntry(entry);
            zos.write(photos.get(id));
            zos.closeEntry();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        try (FileInputStream fis = new FileInputStream(file)) {
            resp.setContentType("application/force-download");

            resp.setHeader("Content-Disposition", "attachment; filename=" + Long.toString(id) + ".zip");
            resp.setContentLength((int) file.length());
            IOUtils.copy(fis, resp.getOutputStream());
            resp.flushBuffer();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        if (file.exists()) {
            file.delete();
        }
    }

    @RequestMapping(value = "/delete_selected", method = RequestMethod.POST)
    public String onDeleteSelected(@RequestBody String s) {
        String pr = s;
        String[] pars = null;
        if (pr != null) {
            pars = pr.split("&");
        }
        for (int i = 0; i < pars.length; i++) {
            String[] part = null;
            part = pars[i].split("=");
            photos.remove(Long.valueOf(part[1]));
        }
        return "index";
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null)
            throw new PhotoNotFoundException();
        else
            return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
