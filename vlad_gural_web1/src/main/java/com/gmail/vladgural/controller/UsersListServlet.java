package com.gmail.vladgural.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/userslist")
public class UsersListServlet extends HttpServlet {
    private LogPassList lpList = LogPassList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = new Users();

        for (LogPass lp : lpList.getList()) {
            String str = null;
            str = lp.getLogin();
            str += " -> access is ";
            str += lp.isAccess();
            users.addUser(str);
        }

        resp.setContentType("application/json");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(users);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
