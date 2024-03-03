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
// import com.github.mohajel.IE.CA2.utils.MizdooniError;


@WebServlet(name = "Login", urlPatterns = { "/login" })
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // // Set the content type of the response to "text/html"
        // response.setContentType("text/html");

        // // Create or retrieve the data you want to pass to the JSP file
        // String message = "login";

        // // Set the data as an attribute in the request object
        // request.setAttribute("message", message);

        // // Get the RequestDispatcher for the JSP file
        // RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/login.jsp");

        // // Forward the request and response objects to the JSP file

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/login.jsp");
        dispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            JSONObject input = new JSONObject();
            input.put("username", username);
            input.put("password", password);

            MizdooniApp app = MizdooniApp.getInstance();
            JSONObject output = app.login(input);

            if (output.getBoolean("success") == true) {
                response.sendRedirect("/");
                log("Login successful");
            } else {
                response.sendRedirect("/login");
                log("Login failed");
            }

            // assertEquals(res.getBoolean("success"), false);
            // assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_TIME_PASSED);

            
            log("username:" + username);
            log("password:" + password);
        } catch (Exception e) {
            // Handle the exception here
            e.printStackTrace();
            response.sendRedirect("/login");
        }
    }
}