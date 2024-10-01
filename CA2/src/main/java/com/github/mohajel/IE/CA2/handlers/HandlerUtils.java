package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class HandlerUtils {

    public static void createNotification(HttpServletRequest request, String message, String type, String redirectURL) {
        JSONObject notification = new JSONObject();
        notification.put("message", message);
        notification.put("type", type);
        notification.put("redirectURL", redirectURL);

        request.getSession().setAttribute("notification", notification);
    }

    public static void redirectToHome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }

    public static void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        dispatcher.forward(request, response);
    }
    
}
