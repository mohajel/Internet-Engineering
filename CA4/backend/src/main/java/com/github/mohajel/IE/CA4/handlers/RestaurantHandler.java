package com.github.mohajel.IE.CA4.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.github.mohajel.IE.CA4.MizdooniApp;
import com.github.mohajel.IE.CA4.models.User;

@WebServlet(name = "RestaurantHandler", urlPatterns = { "/restaurant" })
public class RestaurantHandler extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String restaurantName = request.getParameter("name");
        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject outputMizdooni = app.getInfoOfRestaurantByName(new JSONObject().put("name", restaurantName));
        if (!outputMizdooni.getBoolean("success")){
            String errorMessage = outputMizdooni.getJSONObject("data").getString("error");
            HandlerUtils.createNotification(request, errorMessage, "error", "/restaurant?name=" + restaurantName);
            response.sendRedirect("/notification");
            return;
        }
        JSONObject output = new JSONObject();
        output.put("restaurantData", outputMizdooni.get("data"));
        output.put("success", true);
        output.put("title", "RestaurantPage");

        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        JSONObject tables = app.getTablesByRestaurantName(restaurantName);
        output.getJSONObject("data").put("tables", tables);

        request.setAttribute("context", output);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String restaurantName = request.getQueryString().split("=")[1];
        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject output;

        switch (action) {
            case "reserve":
                String userName = app.logedInUser;
                String tableNumber = request.getParameter("table_number");
                String date = request.getParameter("date_time");
                // replace T in date with space
                date = date.replace('T', ' ');

                JSONObject input = new JSONObject();
                input.put("username", userName);
                input.put("restaurantName", restaurantName);
                input.put("tableNumber", tableNumber);
                input.put("datetime", date);

      
                output = app.reserveTable(input);
                if (output.getBoolean("success") == true){
                    HandlerUtils.createNotification(request, "Reservation successful", "success", "/restaurant?name=" + restaurantName);
                } else {
                    String errorMessage = output.getJSONObject("data").getString("error");
                    HandlerUtils.createNotification(request, errorMessage, "error", "/restaurant?name=" + restaurantName);
                }
                response.sendRedirect("/notification");
                return;

            case "feedback":
                Double food_rate = Double.parseDouble(request.getParameter("food_rate"));
                Double service_rate = Double.parseDouble(request.getParameter("service_rate"));
                Double ambiance_rate = Double.parseDouble(request.getParameter("ambiance_rate"));
                Double overall_rate = Double.parseDouble(request.getParameter("overall_rate"));
                String comment = request.getParameter("comment");
                JSONObject feedback = new JSONObject().put("foodRate", food_rate).put("serviceRate", service_rate)
                        .put("ambianceRate", ambiance_rate).put("overallRate", overall_rate).put("comment", comment)
                        .put("restaurantName", restaurantName).put("username", app.logedInUser);
                output = app.addReview(feedback);
                if (output.getBoolean("success")){
                    HandlerUtils.createNotification(request, "feedback successful", "success", "/restaurant?name=" + restaurantName);
                } else {
                    String errorMessage = output.getJSONObject("data").getString("error");
                    HandlerUtils.createNotification(request, errorMessage, "error", "/restaurant?name=" + restaurantName);
                }
                response.sendRedirect("/notification");
                return;
            default:
                break;
        }

        response.sendRedirect(request.getContextPath() + "/restaurant?name=" + restaurantName);
    }
}