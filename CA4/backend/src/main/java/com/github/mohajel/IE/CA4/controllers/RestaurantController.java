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
            return ResponseEntity.ok().body("{}");
        }

        restaurants = sortJSONArray(restaurants, "numberOfStars", SortOrder.DESCENDING);
        return ResponseEntity.ok().body(restaurants.toString());
    }


    @GetMapping("/search") //complete it
    ResponseEntity<String> getSearchRestaurants(
            @RequestParam(value = "restaurantName") String name,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "restaurantType") String type
    ) {

        System.out.println("restaurantName: " + name);
        System.out.println("location: " + location);
        System.out.println("restaurantType: " + type);

        JSONObject restaurant1 = new JSONObject();
        restaurant1.put("numberOfStars", 5);
        restaurant1.put("restaurantName", "KFC");
        restaurant1.put("reviewCount", 100);
        restaurant1.put("restaurantType", "Fast Food");
        restaurant1.put("location", "Tehran");
        restaurant1.put("openStatus", "Open");
        restaurant1.put("durationInfo", "10:00 - 22:00");
        restaurant1.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/2/48899571.webp");
        restaurant1.put("id", "1");


        JSONObject restaurant2 = new JSONObject();
        restaurant2.put("numberOfStars", 4);
        restaurant2.put("restaurantName", "McDonalds");
        restaurant2.put("reviewCount", 200);
        restaurant2.put("restaurantType", "Fast Food");
        restaurant2.put("location", "Tehran");
        restaurant2.put("openStatus", "Open");
        restaurant2.put("durationInfo", "10:00 - 22:00");
        restaurant2.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/3/54305906.webp");
        restaurant2.put("id", "2");



        JSONObject restaurant3 = new JSONObject();
        restaurant3.put("numberOfStars", 3);
        restaurant3.put("restaurantName", "Pizza Hut");
        restaurant3.put("reviewCount", 300);
        restaurant3.put("restaurantType", "Fast Food");
        restaurant3.put("location", "Tehran");
        restaurant3.put("openStatus", "Open");
        restaurant3.put("durationInfo", "10:00 - 22:00");
        restaurant3.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp");
        restaurant3.put("id", "3");



        // JSonArray
        JSONArray topRestaurants = new JSONArray();
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);

        return ResponseEntity.ok().body(topRestaurants.toString());
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