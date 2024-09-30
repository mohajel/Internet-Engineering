package com.github.mohajel.IE.CA6.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA6.MizdooniApp;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @GetMapping("/{restaurantName}")
    public ResponseEntity<String> getReviewsForRestaurant(@PathVariable(value = "restaurantName") String restaurantName) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray reviews = app.getReviewsByRestaurantName(restaurantName);
        return ResponseEntity.ok().body(reviews.toString());
    }

    @GetMapping("/summary/{restaurantName}")
    public ResponseEntity<String> getSummaryForRestaurant(@PathVariable(value = "restaurantName") String restaurantName) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject reviewsSummary = app.getSummaryReviewByRestaurantName(restaurantName);
        return ResponseEntity.ok().body(reviewsSummary.toString());
    }
}
