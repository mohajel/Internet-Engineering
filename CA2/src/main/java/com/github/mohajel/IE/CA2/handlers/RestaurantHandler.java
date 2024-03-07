package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.github.mohajel.IE.CA2.MizdooniApp;
import com.github.mohajel.IE.CA2.models.User;

@WebServlet(name = "RestaurantHandler", urlPatterns = { "/restaurant" })
public class RestaurantHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "RestaurantPage");
        
        response.setContentType("text/html");
        
        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        request.setAttribute("context", output);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantPage.jsp");
        dispatcher.forward(request, response);
    }
}