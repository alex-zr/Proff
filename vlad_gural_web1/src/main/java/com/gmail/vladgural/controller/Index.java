package com.gmail.vladgural.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Index", urlPatterns = "/")
public class Index extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> strList = Arrays.asList("Audy","Porshe","BMW");

        request.setAttribute("Models",strList);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
