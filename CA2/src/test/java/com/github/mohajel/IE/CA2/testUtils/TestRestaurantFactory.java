package com.github.mohajel.IE.CA2.testUtils;

import org.json.JSONObject;

public class TestRestaurantFactory {

    static public JSONObject createSimpleRestaurant(String name, String managerUsername) {
        JSONObject restaurant = new JSONObject();

        restaurant.put("name", name);
        restaurant.put("managerUsername", managerUsername);
        restaurant.put("type", "Iranian");
        restaurant.put("startTime", "10:00");
        restaurant.put("endTime", "22:00");
        restaurant.put("description", "This is a test restaurant");

        JSONObject address = new JSONObject();
        address.put("country", "Iran");
        address.put("city", "Tehran");
        address.put("street", "Valiasr");

        restaurant.put("address", address);

        return restaurant;
    }

    static public JSONObject createRestaurantInvalidHours(String name, String managerUsername) {
        JSONObject restaurant = new JSONObject();

        restaurant.put("name", name);
        restaurant.put("managerUsername", managerUsername);
        restaurant.put("type", "Iranian");
        restaurant.put("startTime", "08:00");
        restaurant.put("endTime", "12:20");
        restaurant.put("description", "This is a test restaurant");

        JSONObject address = new JSONObject();
        address.put("country", "Iran");
        address.put("city", "Tehran");
        address.put("street", "Valiasr");

        restaurant.put("address", address);

        return restaurant;
    }
}
