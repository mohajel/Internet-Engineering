package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody String body) {
        MizdooniApp app = MizdooniApp.getInstance();

        logger.info("Signup Request: " + body);
        JSONObject bodyJson = new JSONObject(body);
        JSONObject result = app.addUser(bodyJson);
        
        logger.info("Signup Response: \n" + result.toString());
        return ResponseEntity.ok().body(result.toString());
        // return result.toString();
    }
}