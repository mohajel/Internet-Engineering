package com.github.mohajel.IE.CA1;

import com.github.mohajel.IE.CA1.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA1.testUtils.TestTableFactory;
import com.github.mohajel.IE.CA1.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA1.utils.MizdooniError;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MizdooniReserveTableTest {

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
    public void testReserveTable_Valid() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertTrue(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getInt("reservationNumber"), 1);
    }

    @Test
    public void testReserveTable_userDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user2");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_DOES_NOT_EXIST);
    }

    @Test
    public void testReserveTable_ManagerCannotReserve() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "manager1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_IS_MANAGER);
    }

    @Test
    public void testReserveTable_HourNotRounded() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:30");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.HOUR_IS_NOT_ROUND);
    }

    @Test
    public void testReserveTable_InvalidDatetimeFormat() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.DATETIME_FORMAT_INVALID);
    }

    @Test
    public void testReserveTable_RestaurantDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant2");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
    }

    @Test
    public void testReserveTable_TableDoesNotExist() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 2);
        reservation.put("datetime", "2024-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.TABLEID_IN_RESTAURANT_DOES_NOT_EXIST);
    }

    @Test
        public void testReserveTable_TableIsReserved() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 13:00");
        app.reserveTable(reservation);
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.TABLE_IS_RESERVED);
    }

    @Test
    public void testReserveTable_DateTimeIsPassed() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2020-12-01 13:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.DATETIME_IS_PASSED);
    }

    @Test
    public void testReserveTable_DateTimeIsNotInOpenHours() {
        JSONObject reservation = new JSONObject();
        reservation.put("username", "user1");
        reservation.put("restaurantName", "restaurant1");
        reservation.put("tableNumber", 1);
        reservation.put("datetime", "2024-12-01 05:00");
        JSONObject res = app.reserveTable(reservation);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.DATETIME_IS_NOT_IN_OPEN_HOURS);
    }
}