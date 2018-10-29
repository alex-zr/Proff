package com.savkova.webapp.sample_jstl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Table", urlPatterns = "/")
public class TableServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Map<Integer, String> products = new HashMap<>();
        for (int i = 0; i < 5; i++)
        {
            products.put((i + 1), "product " + (i + 1));
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
    }

}
