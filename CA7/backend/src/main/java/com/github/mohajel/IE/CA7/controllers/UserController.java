package com.github.mohajel.IE.CA7.controllers;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA7.MizdooniApp;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody String body) {
        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject bodyJson = new JSONObject(body);
        JSONObject result = app.addUser(bodyJson);
        return ResponseEntity.ok().body(result.toString());
    }
}