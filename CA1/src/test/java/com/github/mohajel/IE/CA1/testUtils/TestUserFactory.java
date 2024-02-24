package com.github.mohajel.IE.CA1.testUtils;

import org.json.JSONObject;

public class TestUserFactory {

    static public JSONObject createSimpleUser(String name, String email) {
        JSONObject user = new JSONObject();

        user.put("role", "client");
        user.put("username", name);
        user.put("password", "1234");
        user.put("email", email);

        JSONObject address = new JSONObject();
        address.put("country", "Iran");
        address.put("city", "Tehran");

        user.put("address", address);

        return user;
    }

    static public JSONObject createUserInvalidUserName() {
        JSONObject user = new JSONObject();


        user.put("role", "client");
        user.put("username", "user1@");
        user.put("password", "1234");
        user.put("email", "user1@gmail.com");
        
        JSONObject address = new JSONObject();
        address.put("country", "Iran");
        address.put("city", "Tehran");
        
        user.put("address", address);
        
        return user;
    }

    static public JSONObject createUserInvalidEmail() {
        JSONObject user = new JSONObject();


        user.put("role", "client");
        user.put("username", "user1");
        user.put("password", "1234");
        user.put("email", "user1.gmail.com");
        
        JSONObject address = new JSONObject();
        address.put("country", "Iran");
        address.put("city", "Tehran");
        
        user.put("address", address);
        
        return user;
    }
}