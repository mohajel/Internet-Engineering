package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name = "RestaurantHandler", urlPatterns = { "/restaurant" })
public class RestaurantHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //                      look at these and then remove this comments

        // // Set the content type of the response to "text/html"
        // response.setContentType("text/html");

        // // Create or retrieve the data you want to pass to the JSP file
        // String message = "login";

        // // Set the data as an attribute in the request object
        // request.setAttribute("message", message);

        // // Get the RequestDispatcher for the JSP file
        // RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/login.jsp");

        // // Forward the request and response objects to the JSP file

        // MizdooniApp app = MizdooniApp.getInstance();

        //                                  what ever u want to 
        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "LoginPage");
        output.put("data", "login");

        
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantPage.jsp");
        dispatcher.forward(request, response);

    }
}