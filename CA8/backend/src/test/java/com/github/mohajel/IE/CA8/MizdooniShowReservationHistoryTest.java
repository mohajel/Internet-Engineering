package com.github.mohajel.IE.CA8;

import com.github.mohajel.IE.CA8.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA8.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA8.testUtils.TestUserFactory;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MizdooniShowReservationHistoryTest {

    private MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
        JSONObject manager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(manager);
        JSONObject restaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(restaurant);
        JSONObject validTable = TestTableFactory.createSimpleTable(1, 4, "restaurant1", "manager1");
        app.addTable(validTable);
        JSONObject validUser = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        app.addUser(validUser);

    }

    @Test
    public void testShowReservationHistory_1Reservation() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        app.reserveTable(reservation);

        JSONObject res = app.showReservationHistory(new JSONObject().put("username", "user1"));
        assertTrue(res.getBoolean("success"));

        JSONObject expected = new JSONObject();
        expected.put("reservationNumber", 1);
        expected.put("restaurantName", "restaurant1");
        expected.put("tableNumber", 1);
        expected.put("datetime", "2024-12-01 13:00");
        assertTrue(compareReservations(res.getJSONObject("data").getJSONArray("reservationHistory").getJSONObject(0), expected));
    }

    @Test
    public void testShowReservationHistory_1ReservationsOf2() {
        JSONObject validUser2 = TestUserFactory.createSimpleUser("user2", "user2@gmail.com");
        app.addUser(validUser2);

        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        app.reserveTable(reservation);

        reservation = new JSONObject();
        reservation.put("username", "user2");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2025-12-01 13:00");
        app.reserveTable(reservation);

        JSONObject res = app.showReservationHistory(new JSONObject().put("username", "user1"));
        assertTrue(res.getBoolean("success"));
        assertEquals(1, res.getJSONObject("data").getJSONArray("reservationHistory").length());
        JSONObject expected = new JSONObject();
        expected.put("reservationNumber", 1);
        expected.put("restaurantName", "restaurant1");
        expected.put("tableNumber", 1);
        expected.put("datetime", "2024-12-01 13:00");
        assertTrue(compareReservations(res.getJSONObject("data").getJSONArray("reservationHistory").getJSONObject(0), expected));
    }

    @Test
    public void testShowReservationHistory_NoReservations() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        app.reserveTable(reservation);

        JSONObject res = app.showReservationHistory(new JSONObject().put("username", "user2"));
        assertTrue(res.getBoolean("success"));
        assertEquals(0, res.getJSONObject("data").getJSONArray("reservationHistory").length());
    }

    public static boolean compareReservations(JSONObject res1, JSONObject res2) {
        return res1.getInt("reservationNumber") == res2.getInt("reservationNumber") &&
                res1.getString("restaurantName").equals(res2.getString("restaurantName")) &&
                res1.getInt("tableNumber") == res2.getInt("tableNumber") &&
                res1.getString("datetime").equals(res2.getString("datetime"));
    }

}
