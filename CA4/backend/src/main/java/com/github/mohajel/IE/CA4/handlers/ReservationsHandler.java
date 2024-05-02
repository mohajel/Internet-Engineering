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

@WebServlet(name = "ReservationsHandler", urlPatterns = { "/reservations" })
public class ReservationsHandler extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MizdooniApp app = MizdooniApp.getInstance();
        String username = app.loggedInUser;

        JSONObject outputMizdooni = app.showReservationHistory(new JSONObject().put("username", username));

        if (!outputMizdooni.getBoolean("success")){
            String errorMessage = outputMizdooni.getJSONObject("data").getString("error");
            HandlerUtils.createNotification(request, errorMessage, "error", "/");
            response.sendRedirect("/notification");
            return;
        }

        JSONObject output = new JSONObject();
        output.put("ReservationData", outputMizdooni);
        output.put("success", true);
        output.put("title", "ReservationsPage");

        if(app.loggedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.loggedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        request.setAttribute("context", output);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/reservationsPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reservationNumber = request.getParameter("action");

        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject inputMizdooni = new JSONObject().put("reservationNumber", reservationNumber).put("username", app.loggedInUser);
        JSONObject outputMizdooni = app.cancelReservation(inputMizdooni);
        if (!outputMizdooni.getBoolean("success")){
            String errorMessage = outputMizdooni.getJSONObject("data").getString("error");
            HandlerUtils.createNotification(request, errorMessage, "error", "/reservations");
            response.sendRedirect("/notification");
            return;
        } else {
            String successMessage = "Reservation " + reservationNumber + " has been cancelled successfully.";
            HandlerUtils.createNotification(request, successMessage, "success", "/reservations");
            response.sendRedirect("/notification");
            return;
        }
    }
}