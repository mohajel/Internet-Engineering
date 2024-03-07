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

@WebServlet(name = "HomeHandler", urlPatterns = { "/" })
public class HomeHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // // Set the content type of the response to "text/html"
        // response.setContentType("text/html");

        // // Create or retrieve the data you want to pass to the JSP file
        // String message = "Hello from the servlet!";

        // // Set the data as an attribute in the request object
        // request.setAttribute("message", message);

        // // Get the RequestDispatcher for the JSP file
        // RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/sth.jsp");

        // // Forward the request and response objects to the JSP file
        // dispatcher.forward(request, response);

        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "HomePage");
        output.put("data", "login");
        
        response.setContentType("text/html");
        
        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser); // changing this to session in future
            output.put("message", "Welcome to Mizdooni dear " + user.userName);
            output.put("icon", "success");
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
            RequestDispatcher dispatcher = null;
            if (user.role == User.Role.CLIENT) {
                dispatcher = request.getRequestDispatcher("./templates/clientHomePage.jsp");
            } else { //MANAGER
                JSONObject managersRestaurant = app.getRestaurantByManagerUsername(user.userName);
                JSONObject restaurantsTables = new JSONObject();
                if (!managersRestaurant.isEmpty()) {
                    restaurantsTables = app.getTablesByRestaurantName(managersRestaurant.getString("name"));    
                }
                output.getJSONObject("data").put("restaurant", managersRestaurant);
                output.getJSONObject("data").put("tables", restaurantsTables);
                dispatcher = request.getRequestDispatcher("./templates/managerHomePage.jsp");
            }
            request.setAttribute("context", output);
            dispatcher.forward(request, response);;
        }

    }
}