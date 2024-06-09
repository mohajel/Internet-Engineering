package com.github.mohajel.IE.CA8;

import com.github.mohajel.IE.CA8.testUtils.TestRestaurantFactory;
import com.github.mohajel.IE.CA8.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA8.utils.MizdooniError;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class MizdooniSearchRestaurantsTest {

    private MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
    }

    /// testSearchRestaurantsByName
    @Test
    public void testSearchRestaurantsByName_FoundExactlyMatch() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject res = app.searchRestaurantsContainName(new JSONObject().put("name", "restaurant1"));

        validRestaurant.remove("managerUsername");
        assertTrue(res.getBoolean("success"));
        assertTrue(compareRestaurantsJSON(res.getJSONObject("data").getJSONArray("restaurants").getJSONObject(0), validRestaurant));
    }

    @Test
    public void testSearchRestaurantsByName_FoundContain() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("FranceParisEiffel", "manager1");
        app.addRestaurant(validRestaurant);
        JSONObject validRestaurant2 = TestRestaurantFactory.createSimpleRestaurant("FranceEiffel", "manager1");
        app.addRestaurant(validRestaurant2);

        JSONObject res = app.searchRestaurantsContainName(new JSONObject().put("name", "Paris"));

        assertTrue(res.getBoolean("success"));
        JSONArray restaurants = res.getJSONObject("data").getJSONArray("restaurants");
        assertEquals(1, restaurants.length());
        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(0), validRestaurant));
    }

    @Test
    public void testSearchRestaurantsByName_NotFound() {
        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
        app.addUser(validManager);
        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(validRestaurant);

        JSONObject res = app.searchRestaurantsContainName(new JSONObject().put("name", "restaurant2"));

        assertFalse(res.getBoolean("success"));
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);

    }

    /// testSearchRestaurantsByType
//    @Test
//    public void testSearchRestaurantsByType_Found1() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        String type = validRestaurant.getString("type");
//        app.addRestaurant(validRestaurant);
//
//        JSONObject res = app.searchRestaurantsByType(new JSONObject().put("type", type));
//        validRestaurant.remove("managerUsername");
//
//        assertTrue(res.getBoolean("success"));
//
//        res = res.getJSONObject("data").getJSONArray("restaurants").getJSONObject(0);
//        assertTrue(compareRestaurantsJSON(res, validRestaurant));
//    }

//    @Test
//    public void testSearchRestaurantsByType_Found2() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        String type = validRestaurant.getString("type");
//        app.addRestaurant(validRestaurant);
//        JSONObject validRestaurant2 = TestRestaurantFactory.createSimpleRestaurant("restaurant2", "manager1");
//        app.addRestaurant(validRestaurant2);
//        JSONObject validRestaurant3 = TestRestaurantFactory.createSimpleRestaurant("restaurant3", "manager1");
//        validRestaurant3.put("type", "Japanese");
//        app.addRestaurant(validRestaurant3);
//
//        JSONObject res = app.searchRestaurantsByType(new JSONObject().put("type", type));
//
//        assertTrue(res.getBoolean("success"));
//
//        JSONArray restaurants = res.getJSONObject("data").getJSONArray("restaurants");
//        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(0), validRestaurant));
//        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(1), validRestaurant2));
//    }

//    @Test
//    public void testSearchRestaurantsByType_NotFound() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        validRestaurant.put("type", "Japanese");
//        app.addRestaurant(validRestaurant);
//
//        JSONObject res = app.searchRestaurantsByType(new JSONObject().put("type", "Italian"));
//
//        assertFalse(res.getBoolean("success"));
//        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
//    }

    /// testSearchRestaurantsByCity

//    @Test
//    public void testSearchRestaurantsByCity_Found1() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        validRestaurant.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant);
//        JSONObject validRestaurant2 = TestRestaurantFactory.createSimpleRestaurant("restaurant2", "manager1");
//        validRestaurant2.getJSONObject("address").put("city", "Tehran");
//        app.addRestaurant(validRestaurant2);
//
//        JSONObject res = app.searchRestaurantsByCity(new JSONObject().put("city", "Paris"));
//
//        assertTrue(res.getBoolean("success"));
//        JSONArray restaurants = res.getJSONObject("data").getJSONArray("restaurants");
//        assertEquals(1, restaurants.length());
//        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(0), validRestaurant));
//    }

//    @Test
//    public void testSearchRestaurantsByCity_Found2() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        validRestaurant.getJSONObject("address").put("city", "Tehran");
//        app.addRestaurant(validRestaurant);
//        JSONObject validRestaurant2 = TestRestaurantFactory.createSimpleRestaurant("restaurant2", "manager1");
//        validRestaurant2.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant2);
//        JSONObject validRestaurant3 = TestRestaurantFactory.createSimpleRestaurant("restaurant3", "manager1");
//        validRestaurant3.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant3);
//
//        JSONObject res = app.searchRestaurantsByCity(new JSONObject().put("city", "Paris"));
//
//        assertTrue(res.getBoolean("success"));
//        JSONArray restaurants = res.getJSONObject("data").getJSONArray("restaurants");
//        assertEquals(2, restaurants.length());
//        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(0), validRestaurant2));
//        assertTrue(compareRestaurantsJSON(restaurants.getJSONObject(1), validRestaurant3));
//    }

//    @Test
//    public void testSearchRestaurantsByCity_NotFound() {
//        JSONObject validManager = TestUserFactory.createSimpleManager("manager1", "manager1@gmail.com");
//        app.addUser(validManager);
//        JSONObject validRestaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
//        validRestaurant.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant);
//        JSONObject validRestaurant2 = TestRestaurantFactory.createSimpleRestaurant("restaurant2", "manager1");
//        validRestaurant2.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant2);
//        JSONObject validRestaurant3 = TestRestaurantFactory.createSimpleRestaurant("restaurant3", "manager1");
//        validRestaurant3.getJSONObject("address").put("city", "Paris");
//        app.addRestaurant(validRestaurant3);
//
//        JSONObject res = app.searchRestaurantsByCity(new JSONObject().put("city", "Tehran"));
//
//        assertFalse(res.getBoolean("success"));
//        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
//    }

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
