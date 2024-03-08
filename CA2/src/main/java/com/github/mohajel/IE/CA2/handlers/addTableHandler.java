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

@WebServlet(name = "addTableHandler", urlPatterns = { "/addTable" })
public class addTableHandler extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MizdooniApp app = MizdooniApp.getInstance();

        String tableNumber = request.getParameter("table_number");
        String seatsNumber = request.getParameter("seats_number");
        String restaurantName = request.getParameter("restaurant_name");

        JSONObject input = new JSONObject();
        input.put("tableNumber", tableNumber);
        input.put("restaurantName", restaurantName);
        input.put("managerUsername", app.logedInUser);
        input.put("seatsNumber", seatsNumber);

        JSONObject output = app.addTable(input);
        request.setAttribute("context", output);

        if (output.getBoolean("success") == true) {
            HandlerUtils.createNotification(request, "Table added successfully", "success", "/");
        } else {
            String errorMessage = output.getJSONObject("data").getString("error");
            HandlerUtils.createNotification(request, errorMessage, "error", "/");  
        }
        response.sendRedirect("/notification");
    }
}