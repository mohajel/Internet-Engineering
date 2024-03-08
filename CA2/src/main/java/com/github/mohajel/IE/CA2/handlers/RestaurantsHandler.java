package com.github.mohajel.IE.CA2.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.mohajel.IE.CA2.MizdooniApp;
import com.github.mohajel.IE.CA2.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

// import com.github.mohajel.IE.CA2.MizdooniApp;
// import com.github.mohajel.IE.CA2.utils.MizdooniError;


@WebServlet(name = "RestaurantsHandler", urlPatterns = { "/restaurants" })
public class RestaurantsHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject outputMizdooni = new JSONObject();
        JSONArray restaurants = new JSONArray();
        if (request.getParameter("mode").equals("withoutFilter")) {
            outputMizdooni = app.showAllRestaurantWithAVGRate();
            restaurants = outputMizdooni.getJSONObject("data").getJSONArray("restaurants");
        }

        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "restaurantsPage");

        StringBuilder HTMLTable = getStringBuilder(restaurants);

        output.put("restaurants", HTMLTable.toString());


        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantsPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String search = request.getParameter("search");
        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject outputMizdooni = new JSONObject();
        JSONArray restaurants = new JSONArray();

        switch (action) {
            case "search_by_type":
                outputMizdooni = app.searchRestaurantsByType(new JSONObject().put("type", search));
                restaurants = outputMizdooni.getJSONObject("data").getJSONArray("restaurants");
                break;
            case "search_by_name":
                outputMizdooni = app.searchRestaurantsContainName(new JSONObject().put("name", search));
                restaurants = outputMizdooni.getJSONObject("data").getJSONArray("restaurants");
                break;
            case "search_by_city":
                outputMizdooni = app.searchRestaurantsByCity(new JSONObject().put("city", search));
                restaurants = outputMizdooni.getJSONObject("data").getJSONArray("restaurants");
                break;
            case "clear":
                outputMizdooni = app.showAllRestaurantWithAVGRate();
                restaurants = outputMizdooni.getJSONObject("data").getJSONArray("restaurants");
                break;
            default:
                break;
        }

        // TODO: handle errors when no restaurants found


        JSONObject output = new JSONObject();
        output.put("success", true);
        output.put("title", "restaurantsPage");
        StringBuilder HTMLTable = getStringBuilder(restaurants);
        output.put("restaurants", HTMLTable.toString());


        if(app.logedInUser.length() == 0){
            response.sendRedirect("/login");
            log("Sending to login page to login first");
        } else {
            User user  = app.db.getUserByUserName(app.logedInUser);
            output.put("data", new JSONObject().put("username", user.userName).put("role", user.role));
        }
        request.setAttribute("context", output);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./templates/restaurantsPage.jsp");
        dispatcher.forward(request, response);
    }

    private static StringBuilder getStringBuilder(JSONArray restaurants) {
        StringBuilder HTMLTable = new StringBuilder();
        for (int i = 0; i < restaurants.length(); i++) {
            JSONObject restaurant = restaurants.getJSONObject(i);
            HTMLTable.append("<tr>");
            HTMLTable.append("<td>").append(String.valueOf(i)).append("</td>");
            String link = "/restaurant/" + restaurant.getString("name");
            HTMLTable.append("<td><a href=").append(link).append(">").append(restaurant.getString("name")).append("</a></td>");
            HTMLTable.append("<td>").append(restaurant.getJSONObject("address").getString("city")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getString("type")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getString("startTime")).append(" - ").append(restaurant.getString("endTime")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getJSONObject("rate").getDouble("serviceRate")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getJSONObject("rate").getDouble("foodRate")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getJSONObject("rate").getDouble("ambianceRate")).append("</td>");
            HTMLTable.append("<td>").append(restaurant.getJSONObject("rate").getDouble("overallRate")).append("</td>");
        }
        return HTMLTable;
    }
}