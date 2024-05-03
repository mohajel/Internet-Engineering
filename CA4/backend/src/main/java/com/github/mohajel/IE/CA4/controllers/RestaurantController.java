package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.json.JSONObject;

import org.json.JSONArray;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

import static com.github.mohajel.IE.CA4.utils.Utils.sortJSONArray;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

     @GetMapping({"/", ""})
     ResponseEntity<String> getAllRestaurantCards() {
            MizdooniApp app = MizdooniApp.getInstance();
            JSONArray restaurants = app.getAllRestaurantCards();

            return ResponseEntity.ok().body(restaurants.toString());
        }


    @GetMapping("/{name}")
    ResponseEntity<String> getRestaurantsWithName(@PathVariable(value = "name") String name) {
        MizdooniApp app = MizdooniApp.getInstance();

        JSONObject restaurant = app.getRestaurantCardByName(name);
        return ResponseEntity.ok().body(restaurant.toString());
    }

    @GetMapping("/topRated")
    ResponseEntity<String> getTopRateRestaurants() {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray restaurants = app.getAllRestaurantCards();

        if (restaurants.isEmpty()) {
            return ResponseEntity.ok().body("[]");
        }

        restaurants = sortJSONArray(restaurants, "numberOfStars", SortOrder.DESCENDING);
        return ResponseEntity.ok().body(restaurants.toString());
    }


    @GetMapping("/search") //complete it
    ResponseEntity<String> getSearchRestaurants(
            @RequestParam(value = "restaurantName") String name,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "restaurantType") String restaurantType
    ) {
        System.out.println("restaurantName: " + name);
        System.out.println("location: " + location);
        System.out.println("restaurantType: " + restaurantType);

        MizdooniApp app = MizdooniApp.getInstance();
        String body = "";

        if (name.isEmpty() && location.isEmpty() && restaurantType.isEmpty()) {
            body = "[]";
        } else if (!name.isEmpty()) {
            body = app.getRestaurantCardsContainName(name).toString();
        } else if (!location.isEmpty()) {
            body = app.searchRestaurantCardsByCity(location).toString();
        } else if (!restaurantType.isEmpty()) {
            body = app.getRestaurantCardsByType(restaurantType).toString();
        }

        return ResponseEntity.ok().body(body);
    }


    @GetMapping("/suggested")
    ResponseEntity<String> getSuggestions() {
        MizdooniApp app = MizdooniApp.getInstance();
        String city = app.cityOfLoggedInUser();
        JSONArray restaurants;

        if (city.isEmpty()) {
            restaurants = app.getAllRestaurantCards();
        } else {
            restaurants = app.searchRestaurantCardsByCity(city);
        }

        return ResponseEntity.ok().body(restaurants.toString());
    }
}