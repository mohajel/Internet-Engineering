package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mohajel.IE.CA2.MizdooniApp;


@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MizdooniApp app = MizdooniApp.getInstance();
        app.logedInUser = "";

        response.sendRedirect("/login");

    }
}