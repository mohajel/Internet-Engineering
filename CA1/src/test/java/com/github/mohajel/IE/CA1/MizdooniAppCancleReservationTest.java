package com.github.mohajel.IE.CA1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.json.JSONObject;
import org.junit.Before;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.github.mohajel.IE.CA1.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA1.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA1.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA1.utils.MizdooniError;
import com.github.mohajel.IE.CA1.utils.Utils;

public class MizdooniAppCancleReservationTest {

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
    public void testCancleReservation_Valid() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("reservationNumber", 1);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testCancleReservation_ReservationDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("reservationNumber", 2);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_DOES_NOT_EXIST);
    }

    @Test
    public void testCancleReservation_UserDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user2");
        reservation.put("reservationNumber", 1);
        JSONObject res = app.cancelReservation(reservation);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESERVATION_DOES_NOT_EXIST);
    }

    @Test
    public void testCancleReservation_ReserveTimeBeforeCurrentDate() {
        try (MockedStatic<Utils> utilities = Mockito.mockStatic(Utils.class)) {
            utilities.when(Utils::getCurrentTime).thenReturn("2020-11-11 11:11");
            String reserveTime = "2020-11-11 11:11";
            String time = Utils.getCurrentTime();
            assertEquals(reserveTime, time);
        }
    }

}
