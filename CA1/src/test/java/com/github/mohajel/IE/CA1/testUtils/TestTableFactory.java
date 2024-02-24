package com.github.mohajel.IE.CA1.testUtils;

import org.json.JSONObject;

public class TestTableFactory {

    static public JSONObject createSimpleTable(int id, int capacity, String restaurantName, String managerUserName) {
        JSONObject table = new JSONObject();

        table.put("tableNumber", id);
        table.put("restaurantName", restaurantName);
        table.put("managerUsername", managerUserName);
        table.put("seatsNumber", capacity);

        return table;
    }

    // static public JSONObject createTableInvalidId() {
    //     JSONObject table = new JSONObject();

    //     table.put("id", -1);
    //     table.put("capacity", 4);
    //     table.put("restaurantName", "Mizdooni");
    //     table.put("managerUserName", "manager1");

    //     return table;
    // }

    // static public JSONObject createTableInvalidCapacity() {
    //     JSONObject table = new JSONObject();

    //     table.put("id", 1);
    //     table.put("capacity", -1);
    //     table.put("restaurantName", "Mizdooni");
    //     table.put("managerUserName", "manager1");

    //     return table;
    // }

    // static public JSONObject createTableInvalidRestaurantName() {
    //     JSONObject table = new JSONObject();

    //     table.put("id", 1);
    //     table.put("capacity", 4);
    //     table.put("restaurantName", "");
    //     table.put("managerUserName", "manager1");

    //     return table;
    // }

    // static public JSONObject createTableInvalidManagerUserName() {
    //     JSONObject table = new JSONObject();

    //     table.put("id", 1);
    //     table.put("capacity", 4);
    //     table.put("restaurantName", "Mizdooni");
    //     table.put("managerUserName", "");

    //     return table;
    // }
    
}
