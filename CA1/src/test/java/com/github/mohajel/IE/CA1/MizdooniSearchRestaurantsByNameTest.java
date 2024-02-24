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
    public void testSearchRestaurantsByName_Found() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject res = app.searchRestaurantsByName(new JSONObject().put("name", "restaurant1"));

        validRestaurant.remove("managerUsername");
        assertTrue(res.getBoolean("success"));
        assertTrue(compareRestaurantsJSON(res.getJSONObject("data"), validRestaurant));
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

    private boolean compareRestaurantsJSON(JSONObject res, JSONObject validRestaurant) {
        return res.getString("name").equals(validRestaurant.getString("name")) &&
                res.getString("type").equals(validRestaurant.getString("type")) &&
                res.getString("startTime").equals(validRestaurant.getString("startTime")) &&
                res.getString("endTime").equals(validRestaurant.getString("endTime")) &&
                res.getString("description").equals(validRestaurant.getString("description")) &&
                res.getJSONObject("address").getString("street").equals(validRestaurant.getJSONObject("address").getString("street")) &&
                res.getJSONObject("address").getString("city").equals(validRestaurant.getJSONObject("address").getString("city")) &&
                res.getJSONObject("address").getString("country").equals(validRestaurant.getJSONObject("address").getString("country"));
    }
}
