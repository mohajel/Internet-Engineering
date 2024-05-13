package com.github.mohajel.IE.CA5.controllers;

import org.springframework.http.MediaType;

// import org.apache.tomcat.util.http.parser.MediaType;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA5.models.Address;
import com.github.mohajel.IE.CA5.models.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// DONOT TOUCH THIS FILE
@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/api")
    ResponseEntity apiTest() {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("title", "hii");
        // return output.toString();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(output.toString());
    }

    @GetMapping("/api/{sth}")
    ResponseEntity apiTest(@PathVariable(value = "sth") String sth) {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("title", "hii " + sth);
        // return output.toString();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(output.toString());
    }

    @GetMapping("/api/13")
    ResponseEntity apiTest13() {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("title", "hii 13");
        // return output.toString();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(output.toString());
    }

    @RequestMapping(value = "/abc/{sth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetAbcSth(@PathVariable(value = "sth") String sth) {
        // log the request in the console
        logger.info("Request: " + sth);
        return sth;
    }

    @RequestMapping(value = "/abcd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User PostAbcdSth(@RequestParam(value = "destination") String destination) {
        // public User(String userName, String password, String email, Address address,
        // String role)
        Address address = new Address("city", "street", "postalCode");
        try {
            address = new Address("city", "country");
            User user = new User("userName", "password", "email", address, "role");
            // log the request in the console
            logger.info("User: " + "aaaaaaaaaaaaaaaa");
            return (user);
        } catch (Exception e) {
            // status 404
            logger.error("Error: " + e);
            return (null);
        }
    }

    @RequestMapping(value = "/abcde", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User PostAbcdeSth() {
        // public User(String userName, String password, String email, Address address,
        // String role)
        Address address = new Address("city", "street", "postalCode");
        try {
            address = new Address("city", "country");
            User user = new User("userName", "password", "email", address, "client");
            logger.info("User: " + user);
            return (user);
        } catch (Exception e) {
            logger.error("Error: " + e);
            return (null);
        }
    }

    @GetMapping("/userStatus")
    String getUserStatus() {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("isLogedIn", "true");
        output.put("role", "manager"); // manager or user
        return output.toString();
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String PostTestPost(@RequestBody String body) {

        logger.info("Request: \n" + body);

        JSONObject output = new JSONObject(body);
        output.put("accept", "false");
        // output.put("username", "username");
        // output.put("password", "password");

        return output.toString();
    }

    // @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // public String GetSearch(@ RequestParam(value = "name") String name) {


    //     System.out.println("restaurantName: " + name);
    //     // System.out.println("location: " + location);
    //     // System.out.println("restaurantType: " + type);

    //     JSONObject restaurant1 = new JSONObject();
    //     restaurant1.put("numberOfStars", 5);
    //     restaurant1.put("restaurantName", "KFC");
    //     restaurant1.put("reviewCount", 100);
    //     restaurant1.put("restaurantType", "Fast Food");
    //     restaurant1.put("location", "Tehran");
    //     restaurant1.put("openStatus", "Open");
    //     restaurant1.put("durationInfo", "10:00 - 22:00");
    //     restaurant1.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/2/48899571.webp");
    //     restaurant1.put("id", "1");


    //     JSONObject restaurant2 = new JSONObject();
    //     restaurant2.put("numberOfStars", 4);
    //     restaurant2.put("restaurantName", "McDonalds");
    //     restaurant2.put("reviewCount", 200);
    //     restaurant2.put("restaurantType", "Fast Food");
    //     restaurant2.put("location", "Tehran");
    //     restaurant2.put("openStatus", "Open");
    //     restaurant2.put("durationInfo", "10:00 - 22:00");
    //     restaurant2.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/3/54305906.webp");
    //     restaurant2.put("id", "2");



    //     JSONObject restaurant3 = new JSONObject();
    //     restaurant3.put("numberOfStars", 3);
    //     restaurant3.put("restaurantName", "Pizza Hut");
    //     restaurant3.put("reviewCount", 300);
    //     restaurant3.put("restaurantType", "Fast Food");
    //     restaurant3.put("location", "Tehran");
    //     restaurant3.put("openStatus", "Open");
    //     restaurant3.put("durationInfo", "10:00 - 22:00");
    //     restaurant3.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp");
    //     restaurant3.put("id", "3");



    //     // JSonArray
    //     JSONArray topRestaurants = new JSONArray();
    //     topRestaurants.put(restaurant1);
    //     topRestaurants.put(restaurant2);
    //     topRestaurants.put(restaurant3);
    //     topRestaurants.put(restaurant1);
    //     topRestaurants.put(restaurant2);
    //     topRestaurants.put(restaurant3);

    //     return topRestaurants.toString();
    // }

}