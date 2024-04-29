package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.springframework.http.MediaType;

import org.json.JSONArray;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping({"/", ""})
    ResponseEntity<String> getRestaurantsWithRating() {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray restaurants = app.getAllRestaurantsWithAVGRate();

        return ResponseEntity.ok().body(restaurants.toString());
    }
}