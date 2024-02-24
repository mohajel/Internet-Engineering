package com.github.mohajel.IE.CA1;

import com.github.mohajel.IE.CA1.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA1.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA1.utils.MizdooniError;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class MizdooniSearchRestaurantsByNameTest {

    private MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
    }

    @Test
    public void testSearchRestaurantsByName_Valid() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject res = app.searchRestaurantsByName(new JSONObject().put("name", "restaurant1"));

        validRestaurant.remove("managerUsername");
        assertTrue(res.getBoolean("success"));
//        assertEquals(res.getJSONObject("data"), validRestaurant);
    }

    @Test
    public void testSearchRestaurantsByName_NotFound() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject res = app.searchRestaurantsByName(new JSONObject().put("name", "restaurant2"));

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);

    }
}
