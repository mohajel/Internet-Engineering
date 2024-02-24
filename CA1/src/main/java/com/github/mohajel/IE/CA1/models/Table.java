package com.github.mohajel.IE.CA1.models;

public class Table {

    Table(int id, int capacity, String restaurantName, String managerUserName) {
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
