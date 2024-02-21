package com.github.mohajel.IE.CA1;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.mohajel.IE.CA1.Utils.Utils;

public class MizdooniAppAddUserTest {

    MizdooniApp app;

    @Before
    public void setUp() {
        app = new MizdooniApp();
    }

    @Test
    public void testAddUserValid() {
        JSONObject validUser = Utils.createSilmpleUser("user1", "user1@gmail.com");
        JSONObject res = app.addUser(validUser);
        assertEquals(res.getBoolean("success"), true);
    }

    @Test
    public void testAddUserInvalidUserName() {
        JSONObject invalidUser = Utils.createUserInvalidUserName();
        JSONObject res = app.addUser(invalidUser);
        assertEquals(res.getBoolean("success"), false);
    }

    @Test
    public void testAddUserInvalidEmail() {
        JSONObject invalidUser = Utils.createUserInvalidEmail();
        JSONObject res = app.addUser(invalidUser);
        assertEquals(res.getBoolean("success"), false);
    }

    @Test
    public void testAddUserRepeatedUserName() {
        JSONObject validUser1 = Utils.createSilmpleUser("user1", "user1@gmail.com");
        app.addUser(validUser1);

        JSONObject validUserSameUserName = Utils.createSilmpleUser("user1", "user111@gmail.com");
        JSONObject res = app.addUser(validUserSameUserName);

        assertEquals(res.getBoolean("success"), false);
    }

    @Test
    public void testAddUserRepeatedEmail() {
        JSONObject validUser1 = Utils.createSilmpleUser("user1", "user1@gmail.com");
        app.addUser(validUser1);

        JSONObject validUserSameEmail = Utils.createSilmpleUser("user2", "user1@gmail.com");
        JSONObject res = app.addUser(validUserSameEmail);

        assertEquals(res.getBoolean("success"), false);
    }
    
}
