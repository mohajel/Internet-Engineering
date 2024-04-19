package com.github.mohajel.IE.CA4.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name = "GetJson", urlPatterns = { "/json" })
public class GetJsonHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type of the response to "text/html"
        response.setContentType("text/html");

        JSONObject output =  new JSONObject();
        output.put("name1", "Ali");
        output.put("name2", "Hasan");
        output.put("name3", "Hosain");

        // Create or retrieve the data you want to pass to the JSP file
        String message = "Hello from the servlet!";

        // Set the data as an attribute in the request object
        request.setAttribute("message", message);
        request.setAttribute("data", output);

        // output.toString()


        // Get the RequestDispatcher for the JSP file
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/jsonn.jsp");

        // Forward the request and response objects to the JSP file
        dispatcher.forward(request, response);

    }
}