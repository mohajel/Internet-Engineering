package com.github.mohajel.IE.CA1.models;

public class Reserve {
    public String userName;
    public String restaurantName;
    public int tableId;
    public int reservationId;
    public MizdooniDate reserveDate;

    public boolean isCancelled;

    public Reserve(String userName, String restaurantName, int tableId, MizdooniDate reserveDate) {
        this.userName = userName;
        this.restaurantName = restaurantName;
        this.tableId = tableId;
        this.reserveDate = reserveDate;
        this.isCancelled = false;
    }
}
