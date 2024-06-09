package com.github.mohajel.IE.CA8.models;

import jakarta.persistence.*;
import org.json.JSONObject;

@Entity
public class Dining_Table {

    public Dining_Table(int id, String restaurantName, String managerUserName, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.restaurantName = restaurantName;
        this.managerUserName = managerUserName;
    }

    // Empty constructor for Hibernate
    public Dining_Table() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_database;
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
