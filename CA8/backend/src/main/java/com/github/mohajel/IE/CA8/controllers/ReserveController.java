package com.github.mohajel.IE.CA8.controllers;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA8.MizdooniApp;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @GetMapping("{restaurantName}")
    public ResponseEntity<String> getReservesByRestaurantName(@PathVariable String restaurantName) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray reserves = app.getReservesByRestaurantName(restaurantName);
        return ResponseEntity.ok().body(reserves.toString());
    }
}
