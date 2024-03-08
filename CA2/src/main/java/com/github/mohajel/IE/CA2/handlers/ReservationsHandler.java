package com.github.mohajel.IE.CA2.handlers;

import java.io.FileWriter;
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

@WebServlet(name = "ReservationsHandler", urlPatterns = { "/reservations" })
public class ReservationsHandler extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MizdooniApp app = MizdooniApp.getInstance();
        String username = app.logedInUser;

        JSONObject outputMizdooni = app.showReservationHistory(new JSONObject().put("username", username));
        JSONObject output = new JSONObject();
        output.put("ReservationData", outputMizdooni);
        output.put("success", true);
        output.put("title", "ReservationsPage");

        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        request.setAttribute("context", output);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/reservationsPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        String restaurantName = request.getQueryString().split("=")[1];
//        MizdooniApp app = MizdooniApp.getInstance();
//
//        switch (action) {
//            case "reserve":
//                break;
//            case "feedback":
//                Double food_rate = Double.parseDouble(request.getParameter("food_rate"));
//                Double service_rate = Double.parseDouble(request.getParameter("service_rate"));
//                Double ambiance_rate = Double.parseDouble(request.getParameter("ambiance_rate"));
//                Double overall_rate = Double.parseDouble(request.getParameter("overall_rate"));
//                String comment = request.getParameter("comment");
//                JSONObject feedback = new JSONObject().put("foodRate", food_rate).put("serviceRate", service_rate)
//                        .put("ambianceRate", ambiance_rate).put("overallRate", overall_rate).put("comment", comment)
//                        .put("restaurantName", restaurantName).put("username", app.logedInUser);
//                app.addReview(feedback);
//                break;
//            default:
//                break;
//        }
//
//        response.sendRedirect(request.getContextPath() + "/restaurant?name=" + restaurantName);
    }
}