package com.gmail.vladgural.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/logpass")
public class LogPassServlet extends HttpServlet {

    private LogPassList lpList = LogPassList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String passWord = req.getParameter("password");
        String strResponse;
        boolean access = false;


        for (LogPass lp : lpList.getList()) {
            if (lp.getLogin().equals(login) && lp.getPassWord().equals(passWord)) {
                access = true;
                lp.setAccess(true);
            }
        }

        resp.setContentType("html/text");
        OutputStream os = resp.getOutputStream();

        if (access)
            strResponse = "Access is allowed";
        else
            strResponse = "Access is denied";
        byte buf[] = strResponse.getBytes("UTF-8");
        os.write(buf);
    }
}
