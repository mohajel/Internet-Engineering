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

        try {
            MizdooniApp app = MizdooniApp.getInstance();

            String tableNumber = request.getParameter("table_number");
            String seatsNumber = request.getParameter("seats_number");
            String restaurantName = request.getParameter("restaurant_name");

            // addTable {"tableNumber": 1, "restaurantName": "restaurant1",
            // "managerUsername": "manager1", "seatsNumber" : 4}

            JSONObject input = new JSONObject();
            input.put("tableNumber", tableNumber);
            input.put("restaurantName", restaurantName);
            input.put("managerUsername", app.logedInUser);
            input.put("seatsNumber", seatsNumber);

            JSONObject output = app.addTable(input);
            request.setAttribute("context", output);

            // RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
            // dispatcher.forward(request, response);
            response.sendRedirect("/");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}