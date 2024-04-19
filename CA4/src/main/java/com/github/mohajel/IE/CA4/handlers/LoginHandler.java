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

@WebServlet(name = "LoginHandler", urlPatterns = { "/login" })
public class LoginHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "LoginPage");
        output.put("data", "login");

        if(app.logedInUser.length() == 0){
            output.put("message", "Please login to continue");
            output.put("icon", "warning");
        } else {
            output.put("message", "You are already logged in");
            output.put("icon", "info");
        }
        
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/loginPage.jsp");
        dispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            JSONObject input = new JSONObject();
            input.put("username", username);
            input.put("password", password);

            MizdooniApp app = MizdooniApp.getInstance();
            JSONObject output = app.login(input);

            if (output.getBoolean("success") == true){
                HandlerUtils.createNotification(request, "login successful", "success", "/");
                
            } else {
                String errorMessage = output.getJSONObject("data").getString("error");
                HandlerUtils.createNotification(request, errorMessage, "error", "/login");
            }
            response.sendRedirect("/notification");


            // if (output.getBoolean("success") == true) {
            //     response.sendRedirect("/");
            //     log("Login successful");

            //     request.getSession().invalidate();
            //     request.getSession(true);
                 
            // } else {
            //     request.setAttribute("context", output);
            //     response.setContentType("text/html");
            //     RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/loginPage.jsp");
            //     dispatcher.forward(request, response);
            //     log("Login failed");
            // }

    }
}