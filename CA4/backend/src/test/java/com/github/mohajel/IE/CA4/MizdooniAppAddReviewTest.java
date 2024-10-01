package com.github.mohajel.IE.CA4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.mohajel.IE.CA4.testUtils.*;
import com.github.mohajel.IE.CA4.utils.MizdooniError;

public class MizdooniAppAddReviewTest {

    private MizdooniApp app;

    @Before
    public void setUp() {

        app = new MizdooniApp();
        JSONObject manager = TestUserFactory.createSimpleManager("manager1", "manager@gmail.com");
        app.addUser(manager);
        JSONObject user = TestUserFactory.createSimpleUser("user1", "user@gmail.com");
        app.addUser(user);
        JSONObject restaurant = TestRestaurantFactory.createSimpleRestaurant("restaurant1", "manager1");
        app.addRestaurant(restaurant);
    }

    @Test
    public void testAddReviewValid() {
        JSONObject validReview = TestReviewFactory.createSimpleReview("user1", "restaurant1", 3.4);
        JSONObject res = app.addReview(validReview);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testAddReviewUserIsManager() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("manager1", "restaurant1", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_IS_MANAGER);
    }

    @Test
    public void testAddReviewRestaurantDoesNotExist() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user1", "restaurant2", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.RESTAURANT_DOES_NOT_EXIST);
    }

    @Test
    public void testAddReviewUserDoesNotExist() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user2", "restaurant1", 3.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.USER_DOES_NOT_EXIST);
    }

    @Test
    public void testAddReviewInvalidRate() {
        JSONObject invalidReview = TestReviewFactory.createSimpleReview("user1", "restaurant1", 6.4);
        JSONObject res = app.addReview(invalidReview);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"), MizdooniError.INVALID_RATING);
    }

    @Test
    public void testAddReviewExistedReview() {
        JSONObject validReview = TestReviewFactory.createSimpleReview("user1", "restaurant1", 6.4);
        app.addReview(validReview);

        JSONObject newReview = TestReviewFactory.createSimpleReview("user1", "restaurant1", 1.0);
        newReview.put("comment", "This is a new comment");
        newReview.put("serviceRate", 2.0);
        newReview.put("ambianceRate", 3.0);
        newReview.put("overallRate", 4.0);
        JSONObject res = app.addReview(newReview);

        assertTrue(res.getBoolean("success"));
        assertEquals(res.getString("data"), "Review added successfully.");
    }

}