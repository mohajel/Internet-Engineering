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

import com.github.mohajel.IE.CA4.MizdooniApp;
import com.github.mohajel.IE.CA4.models.Address;
import com.github.mohajel.IE.CA4.models.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class StatusController {
    
    Logger logger = LoggerFactory.getLogger(StatusController.class);


    @RequestMapping(value ="/status", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String getStatus() {
        MizdooniApp app = MizdooniApp.getInstance();
        String user = app.logedInUser;

        JSONObject output = new JSONObject();
        output.put("status", "loggedIn"); // loggedIn or loggedOut
        output.put("role", "manager"); // manager or user
        logger.info("Status: " + output.toString());
        return output.toString();
    }
    

    // @RequestMapping(value ="/abcd", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    // public User PostAbcdSth(@RequestParam(value = "destination") String destination) {
    //     // public User(String userName, String password, String email, Address address, String role)
    //     Address address = new Address("city", "street", "postalCode");
    //     try {
    //         address = new Address("city", "country");
    //         User user = new User("userName", "password", "email", address, "role");
    //         // log the request in the console
    //         logger.info("User: " + "aaaaaaaaaaaaaaaa");
    //         return (user);
    //     } catch (Exception e) {
    //         // status 404
    //         logger.error("Error: " + e);
    //         return (null);
    //     }
    // }

    // @RequestMapping(value ="/abcde", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    // public User PostAbcdeSth() {
    //     // public User(String userName, String password, String email, Address address, String role)
    //     Address address = new Address("city", "street", "postalCode");
    //     try {
    //         address = new Address("city", "country");
    //         User user = new User("userName", "password", "email", address, "client");
    //         logger.info("User: " + user);
    //         return (user);
    //     } catch (Exception e) {
    //         logger.error("Error: " + e);
    //         return (null);
    //     }
    // }

    // @GetMapping("/userStatus")
    // String getUserStatus() {
    //     // return "Hello, World!";

    //     JSONObject output = new JSONObject();
    //     // output.put("success", true);
    //     output.put("isLogedIn", "true");
    //     output.put("role", "manager"); //manager or user
    //     return output.toString();
    // }

}