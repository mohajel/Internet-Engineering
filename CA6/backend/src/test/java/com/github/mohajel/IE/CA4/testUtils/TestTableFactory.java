package com.github.mohajel.IE.CA4.testUtils;

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
}
