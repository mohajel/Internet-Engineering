package com.github.mohajel.IE.CA2;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

// import com.github.mohajel.IE.CA2.MizdooniApp;
import com.github.mohajel.IE.CA2.testUtils.TestUserFactory;
import com.github.mohajel.IE.CA2.utils.MizdooniError;

public class MizdooniAppAddUserTest {

    MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
    }

    @Test
    public void testAddUserValid() {
        JSONObject validUser = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        JSONObject res = app.addUser(validUser);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testAddUserInvalidUserName() {
        JSONObject invalidUser = TestUserFactory.createUserInvalidUserName();
        JSONObject res = app.addUser(invalidUser);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.INVALID_USERNAME_CONTAINS_SPECIAL_CHARACTERS);
    }

    @Test
    public void testAddUserInvalidEmail() {
        JSONObject invalidUser = TestUserFactory.createUserInvalidEmail();
        JSONObject res = app.addUser(invalidUser);
        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.INVALID_EMAIL_FORMAT);
    }

    @Test
    public void testAddUserInvalidRole() {
        JSONObject validUser = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        JSONObject invalidUser = validUser.put("role", "invalid-role");
        JSONObject res = app.addUser(invalidUser);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.INVALID_ROLE);
    }

    @Test
    public void testAddUserInvalidInputFormat() {
        JSONObject invalidUserFormat = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        invalidUserFormat.remove("password"); 
        JSONObject res = app.addUser(invalidUserFormat);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.INVALID_JSON);
    }

    @Test
    public void testAddUserNoCountry() {
        JSONObject invalidUserFormat = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        invalidUserFormat.getJSONObject("address").remove("country");
        System.out.println(invalidUserFormat.toString());
        JSONObject res = app.addUser(invalidUserFormat);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.INVALID_JSON);
    }

    @Test
    public void testAddUserRepeatedUserName() {
        JSONObject validUser1 = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        app.addUser(validUser1);

        JSONObject validUserSameUserName = TestUserFactory.createSimpleUser("user1", "user111@gmail.com");
        JSONObject res = app.addUser(validUserSameUserName);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
                MizdooniError.USER_ALREADY_EXISTS);
    }

    @Test
    public void testAddUserRepeatedEmail() {
        JSONObject validUser1 = TestUserFactory.createSimpleUser("user1", "user1@gmail.com");
        app.addUser(validUser1);

        JSONObject validUserSameEmail = TestUserFactory.createSimpleUser("user2", "user1@gmail.com");
        JSONObject res = app.addUser(validUserSameEmail);

        assertEquals(res.getBoolean("success"), false);
        assertEquals(res.getJSONObject("data").getString("error"),
            MizdooniError.EMAIL_ALREADY_EXISTS);
    }

}
