package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mohajel.IE.CA2.MizdooniApp;
import org.json.JSONArray;
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

        JSONArray restaurants = MizdooniApp.getInstance().showAllRestaurantWithAVGRate();
        StringBuilder HTMLTable = new StringBuilder();
        for (int i = 0; i < restaurants.length(); i++) {
            JSONObject restaurant = restaurants.getJSONObject(i);
            HTMLTable.append("<tr>");
            HTMLTable.append("<th>").append(String.valueOf(i)).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getString("name")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getJSONObject("address").getString("city")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getString("type")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getString("startTime")).append(" - ").append(restaurant.getString("endTime")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getJSONObject("rate").getDouble("serviceRate")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getJSONObject("rate").getDouble("foodRate")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getJSONObject("rate").getDouble("ambianceRate")).append("</th>");
            HTMLTable.append("<th>").append(restaurant.getJSONObject("rate").getDouble("overallRate")).append("</th>");
        }

        output.put("restaurants", HTMLTable.toString());
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantsPage.jsp");
        dispatcher.forward(request, response);

    }
}