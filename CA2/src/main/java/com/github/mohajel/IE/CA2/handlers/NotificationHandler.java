package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet(name = "NotificationHandler", urlPatterns = { "/notification" })
public class NotificationHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
;
        JSONObject output = new JSONObject();
        output.put("title", "Notification");
        request.setAttribute("context", output);
        
        HttpSession session=request.getSession(false);
        if (session == null) {
            response.sendRedirect("/");
            return;
        }
        JSONObject notifications = (JSONObject) session.getAttribute("notification");
        if (notifications == null) {
            response.sendRedirect("/");
            return;
        }
        // delete from session
        session.removeAttribute("notification");
        request.setAttribute("notification", notifications);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/notificationPage.jsp");
        dispatcher.forward(request, response);
    }
}