package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.MediaType;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA4.models.Address;
import com.github.mohajel.IE.CA4.models.User;

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

    @RequestMapping(value ="/abc/{sth}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String GetAbcSth(@PathVariable(value = "sth") String sth) {
        // log the request in the console
        logger.info("Request: " + sth);
        return sth;
    }
    

    @RequestMapping(value ="/abcd", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public User PostAbcdSth(@RequestParam(value = "destination") String destination) {
        // public User(String userName, String password, String email, Address address, String role)
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

    @RequestMapping(value ="/abcde", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public User PostAbcdeSth() {
        // public User(String userName, String password, String email, Address address, String role)
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
        output.put("role", "manager"); //manager or user
        return output.toString();
    }

}