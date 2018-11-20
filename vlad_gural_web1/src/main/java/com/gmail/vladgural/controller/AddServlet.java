package com.gmail.vladgural.controller;

import com.gmail.vladgural.client.Message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();
    private LogPassList lpList = LogPassList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String to;

        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, "UTF-8");

        Message msg = Message.fromJSON(bufStr);
        if (msg == null) {
            return;
        }

        to = msg.getTo();
        if (to == null) {
            msgList.add(msg);
            return;
        }

        for (LogPass lp : lpList.getList()) {
            if (lp.getLogin().equals(to)) {
                msgList.add(msg);
                return;
            }
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
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