package com.savkova.webapp.login_sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/session_manager")
public class SessionStartStopServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getParameter("action");
        String name = req.getParameter("name");

        if (action == null)
        {
            action = "default";
        }

        HttpSession session;
        switch (action)
        {
            case "start":
                session = req.getSession(true);
                session.setAttribute("name", name);
                return;
            case "stop":
                session = req.getSession(false);
                if (session != null) session.invalidate();
                resp.getWriter().write("Session have been destroyed.");
                return;
        }

        session = req.getSession(false);
        if (session == null)
        {
            resp.getWriter().write("No active session.");
            return;
        }

        name = (String) session.getAttribute("name");
        resp.getWriter().write("Name: " + name);
        resp.getWriter().close();
    }
}
