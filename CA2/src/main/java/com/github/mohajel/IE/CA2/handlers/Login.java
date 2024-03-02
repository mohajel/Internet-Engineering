package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mohajel.IE.CA2.MizdooniApp;


@WebServlet(name = "Login", urlPatterns = { "/login" })
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type of the response to "text/html"
        response.setContentType("text/html");

        // Create or retrieve the data you want to pass to the JSP file
        String message = "login";

        // Set the data as an attribute in the request object
        request.setAttribute("message", message);

        // Get the RequestDispatcher for the JSP file
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/login.jsp");

        // Forward the request and response objects to the JSP file
        dispatcher.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log("username:" + username);
        log("password:" + password);

        MizdooniApp app = MizdooniApp.getInstance();

        // response.sendRedirect("/");
        // if (bolbolestan.doesStudentExist(studentId)) {
        //     bolbolestan.makeLoggedIn(studentId);
        //     response.sendRedirect("http://localhost:8080/ca3_war_exploded");
        // } else {
        //     response.sendRedirect("http://localhost:8080/ca3_war_exploded/login");
        // }
    }
}