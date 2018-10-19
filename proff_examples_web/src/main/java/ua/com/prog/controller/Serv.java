package ua.com.prog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/serv")
public class Serv extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws IOException {
        String str1 = req.getParameter("name1");
        String str2 = req.getParameter("name2");

        resp.getWriter().print("<h1>name1 =" + str1 + "</h1><br>");
        resp.getWriter().print("<h1>name2 =" + str2 + "</h1><br>");
    }
}
