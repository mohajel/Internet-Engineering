package com.github.mohajel.IE.CA4.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/github/{code}")
    public ResponseEntity<String> getGithubAuthRedirect(@PathVariable String code) {
        // MizdooniApp app = MizdooniApp.getInstance();
        // JSONArray tables = app.getTablesByRestaurantName(restaurantName);
        JSONObject output = new JSONObject();
        output.put("code", code);
        return ResponseEntity.ok().body(output.toString());
    }
}
