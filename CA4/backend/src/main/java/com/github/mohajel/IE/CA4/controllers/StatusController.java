package com.github.mohajel.IE.CA4.controllers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/api")
    ResponseEntity apiTest() {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("title", "hii");
        // return output.toString();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(output.toString());
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