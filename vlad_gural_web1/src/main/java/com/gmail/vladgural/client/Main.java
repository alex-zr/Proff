package com.gmail.vladgural.client;

import com.gmail.vladgural.controller.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.plaf.synth.SynthToolTipUI;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String login = null;
        String passWord = null;
        String logResponse = null;
        byte[] buf = null;

        Scanner scanner = new Scanner(System.in);
        try {

            // Запросс на ввод догина и пароля
            System.out.println("Enter your login: ");
            login = scanner.nextLine();

            System.out.println("Enter your password: ");
            passWord = scanner.nextLine();


            //Проверка на доступ про помощи
            //сервлета LogPassServlet
            logResponse = checkAccess(login,passWord);

            if(logResponse.equals("Access is denied"))
                return;

            //Запрос на вывод зарегестрированных пользователей
            //сервлет UsersListServlet
            System.out.println("do you want display users list (y/n)");
            if(scanner.nextLine().equals("y")) {
                URL url = new URL(Utils.getURL() + "/userslist");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStream is = http.getInputStream();

                while(true) {
                    buf = requestBodyToArray(is);
                    if(buf.length!=0)
                        break;
                }
                String strBuf = new String(buf, "UTF-8");
                Gson gson = new GsonBuilder().create();
                Users users = gson.fromJson(strBuf,Users.class);

                System.out.println("List users");
                for(String str:users.getList()){
                    System.out.println(str);
                }
                is.close();
            }


            //Запуск потока на вывод сдержимого чата
            Thread th = new Thread(new GetThread(login));
            th.setDaemon(true);
            th.start();

            //Запрос на ввод сообщения и передача его на сервер
            System.out.println("\"Message\" - Meggage to all");
            System.out.println("\"Message -> Login\" - Meggage only to Login");
            System.out.println("Enter your message: ");
            while (true) {
                String[] messTo = null;
                Message m;
                String str = scanner.nextLine();
                if (str.isEmpty()) break;

                messTo = str.split(" -> ");
                if(messTo.length==1)
                    m = new Message(login, null, messTo[0]);
                else
                    m = new Message(login, messTo[1], messTo[0]);

                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static String checkAccess(String login, String passWord) {
        String logResponse = null;
        byte[] buf = null;
        try {
            URL url = new URL(Utils.getURL() + "/logpass?login=" + login +
                    "&password=" + passWord);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            try (InputStream is = http.getInputStream()) {
                while (true) {
                    buf = requestBodyToArray(is);
                    if (buf.length != 0)
                        break;
                }
            } catch (IOException e) {

            }
        } catch (MalformedURLException e){

        } catch (IOException e){

        }
        try {
            logResponse = new String(buf, "UTF-8");
        } catch (UnsupportedEncodingException e){

        }
        System.out.println(logResponse);
        return logResponse;
    }

    public static byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}