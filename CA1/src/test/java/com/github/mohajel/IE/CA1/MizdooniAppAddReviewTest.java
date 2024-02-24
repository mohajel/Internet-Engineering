package com.github.mohajel.IE.CA1;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.mohajel.IE.CA1.testUtils.*;
import com.github.mohajel.IE.CA1.utils.MizdooniError;

public class MizdooniAppAddReviewTest {

    private MizdooniApp app;

    @Before
    public void setUp() {

        app = new MizdooniApp();
        JSONObject manager = TestUserFactory.createSimpleManager("manager1", "manager@gmail.com");
        app.addUser(manager);
        JSONObject user = TestUserFactory.createSimpleUser("user1", "user@gmail.com");
        app.addUser(user);
        JSONObject restaurant = TestRestaurantFactory.createSimpleRestaurant("resturant1", "manager1");
        app.addRestaurant(restaurant);
    }

    @Test
    public void testAddReviewValid() {
        JSONObject validReview = TestReviewFactory.createSimpleReview("user1", "resturant1", 3.4);
        JSONObject res = app.addReview(validReview);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testAddReviewUserIsManager() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("manager1", "resturant1", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_IS_MANAGER);
    }

    @Test
    public void testAddReviewResturantDoesNotExist() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user1", "resturant2", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
    }

    @Test
    public void testAddReviewUserDoesNotExist() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user2", "resturant1", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_DOES_NOT_EXIST);
    }

    @Test
    public void testAddReviewInvalidRate() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user1", "resturant1", 6.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.INVALID_RATING);
    }

}