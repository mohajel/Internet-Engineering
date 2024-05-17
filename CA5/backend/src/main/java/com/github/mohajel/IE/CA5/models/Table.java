package com.github.mohajel.IE.CA5.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.json.JSONObject;

@Entity
public class Table {

    public Table(int id, String restaurantName, String managerUserName, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.restaurantName = restaurantName;
        this.managerUserName = managerUserName;
    }

    // Empty constructor for Hibernate
    public Table() {

    }

    @Id
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
