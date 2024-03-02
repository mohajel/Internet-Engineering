package com.github.mohajel.IE.CA2;

// import com.github.mohajel.IE.CA2.MizdooniApp;
import com.github.mohajel.IE.CA2.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA2.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA2.utils.MizdooniError;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MizdooniAppAddRestaurantTest {

    private MizdooniApp app;

    @Before
    public void setUp() throws MizdooniError{
        app = new MizdooniApp();
    }

    @Test
    public void testAddRestaurantValid() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);

        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        JSONObject res = app.addRestaurant(validRestaurant);

        assertTrue(res.getBoolean("success"));
        assertEquals(res.getString("data"), "Restaurant added successfully.");
    }

    // @Test
    // public void testAddRestaurant_ManagerDoesNotExist() {
    //     JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
    //     JSONObject res = app.addRestaurant(validRestaurant);

    //     assertFalse(res.getBoolean("success"));
    //     assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_DOES_NOT_EXIST);
    // }

    @Test
    public void testAddRestaurant_ManagerIsNotManager() {
        JSONObject validClient = TestUserFactory.createSimpleUser("user1", "user1@gamil.com");
        app.addUser(validClient);

        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "user1");
        JSONObject res = app.addRestaurant(validRestaurant);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_IS_NOT_MANAGER);
    }

    @Test
    public void testAddRestaurant_RestaurantNameAlreadyExists() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);

        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject RestaurantDuplicateName = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        JSONObject res = app.addRestaurant(RestaurantDuplicateName);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_ALREADY_EXISTS);
    }

    @Test
    public void testAddRestaurant_InvalidHour() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);

        JSONObject invalidRestaurant = TestRestaurantFactory.createRestaurantInvalidHours("restaurant1", "manager1");
        JSONObject res = app.addRestaurant(invalidRestaurant);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.HOUR_IS_NOT_ROUND);
    }

    @Test
    public void testAddRestaurant_InvalidAddress() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);

        JSONObject invalidRestaurant = TestRestaurantFactory.createRestaurantInvalidHours("restaurant1", "manager1");
        invalidRestaurant.getJSONObject("address").remove("country");
        JSONObject res = app.addRestaurant(invalidRestaurant);

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.INVALID_JSON);
    }
}
