package com.github.mohajel.IE.CA2.models;

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
}
