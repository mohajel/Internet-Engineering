package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

// import com.github.mohajel.IE.CA2.MizdooniApp;
// import com.github.mohajel.IE.CA2.utils.MizdooniError;


@WebServlet(name = "RestaurantsHandler", urlPatterns = { "/restaurants" })
public class RestaurantsHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "restaurantsPage");
        
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantsPage.jsp");
        dispatcher.forward(request, response);

    }
}