package com.github.mohajel.IE.CA5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.json.JSONObject;
import org.junit.Before;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.github.mohajel.IE.CA5.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA5.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA5.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA5.utils.MizdooniError;
import com.github.mohajel.IE.CA5.utils.Utils;

public class MizdooniAppCancelReservationTest {

    MizdooniApp app;

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
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        app.reserveTable(reservation);
    }

    @Test
    public void testCancelReservation_Valid() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("reservationNumber", 1);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testCancelReservation_ReservationDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("reservationNumber", 2);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_DOES_NOT_EXIST);
    }

    @Test
    public void testCancelReservation_UserDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user2");
        reservation.put("reservationNumber", 1);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_DOES_NOT_EXIST);
    }

    @Test
    public void testCancelReservation_ReserveTimeBeforeCurrentDate() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("reservationNumber", 1);

        try (MockedStatic<Utils> utilities = Mockito.mockStatic(Utils.class)) {
            utilities.when(Utils::getCurrentTime).thenReturn("2025-11-11 11:11");
            JSONObject res = app.cancelReservation(reservation);
            System.out.println(res.toString());
            assertEquals(res.getBoolean("success"), false);
            assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_TIME_PASSED);
        }
    }

}
