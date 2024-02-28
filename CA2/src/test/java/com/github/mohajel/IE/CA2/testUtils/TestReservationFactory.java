package com.github.mohajel.IE.CA2.testUtils;

import org.json.JSONObject;

public class TestReservationFactory {
    public static JSONObject createSimpleReservation(String username, String restaurantName, int tableNumber, String datetime) {
        JSONObject reservation = new JSONObject();
        reservation.put("username", username);
        reservation.put("restaurantName", restaurantName);
        reservation.put("tableNumber", tableNumber);
        reservation.put("datetime", datetime);
        return reservation;
    }
}
