package com.gmail.vladgural.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/get")
public class GetListServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();
    private LogPassList lpList = LogPassList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loginStr = req.getParameter("login");
        LogPass lp = lpList.checkLogin(loginStr);
        int from = lp.getNumberOfReadMessages();
            if (from < 0) {
                from = 0;
            }
        resp.setContentType("application/json");

        String json = msgList.toJSON(from, lp);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes("UTF-8");
            os.write(buf);

            //PrintWriter pw = resp.getWriter();
            //pw.print(json);
        }
    }
}