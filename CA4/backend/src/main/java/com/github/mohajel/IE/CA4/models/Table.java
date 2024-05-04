package com.github.mohajel.IE.CA4.models;

import org.json.JSONObject;

public class Table {

    public Table(int id, String restaurantName, String managerUserName, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.restaurantName = restaurantName;
        this.managerUserName = managerUserName;
    }

    public int id;
    public int capacity;
    public String restaurantName;
    public String managerUserName;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("capacity", capacity);
        return json;
    }
}
