package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.json.JSONObject;
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

    @GetMapping("/topRated")
    ResponseEntity<String> getTopRateRestaurants() {
        JSONObject restaurant1 = new JSONObject();
        restaurant1.put("numberOfStars", 5);
        restaurant1.put("restaurantName", "KFC");
        restaurant1.put("reviewCount", 100);
        restaurant1.put("restaurantType", "Fast Food");
        restaurant1.put("location", "Tehran");
        restaurant1.put("openStatus", "Open");
        restaurant1.put("durationInfo", "10:00 - 22:00");
        restaurant1.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/2/48899571.webp");


        JSONObject restaurant2 = new JSONObject();
        restaurant2.put("numberOfStars", 4);
        restaurant2.put("restaurantName", "McDonalds");
        restaurant2.put("reviewCount", 200);
        restaurant2.put("restaurantType", "Fast Food");
        restaurant2.put("location", "Tehran");
        restaurant2.put("openStatus", "Open");
        restaurant2.put("durationInfo", "10:00 - 22:00");
        restaurant2.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/3/54305906.webp");


        JSONObject restaurant3 = new JSONObject();
        restaurant3.put("numberOfStars", 3);
        restaurant3.put("restaurantName", "Pizza Hut");
        restaurant3.put("reviewCount", 300);
        restaurant3.put("restaurantType", "Fast Food");
        restaurant3.put("location", "Tehran");
        restaurant3.put("openStatus", "Open");
        restaurant3.put("durationInfo", "10:00 - 22:00");
        restaurant3.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp");



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
        JSONObject restaurant1 = new JSONObject();
        restaurant1.put("numberOfStars", 5);
        restaurant1.put("restaurantName", "KFC");
        restaurant1.put("reviewCount", 100);
        restaurant1.put("restaurantType", "Fast Food");
        restaurant1.put("location", "Tehran");
        restaurant1.put("openStatus", "Open");
        restaurant1.put("durationInfo", "10:00 - 22:00");
        restaurant1.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp");

        JSONObject restaurant2 = new JSONObject();
        restaurant2.put("numberOfStars", 4);
        restaurant2.put("restaurantName", "McDonalds");
        restaurant2.put("reviewCount", 200);
        restaurant2.put("restaurantType", "Fast Food");
        restaurant2.put("location", "Tehran");
        restaurant2.put("openStatus", "Open");
        restaurant2.put("durationInfo", "10:00 - 22:00");
        restaurant2.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/3/54305906.webp");


        JSONObject restaurant3 = new JSONObject();
        restaurant3.put("numberOfStars", 3);
        restaurant3.put("restaurantName", "Pizza Hut");
        restaurant3.put("reviewCount", 300);
        restaurant3.put("restaurantType", "Fast Food");
        restaurant3.put("location", "Tehran");
        restaurant3.put("openStatus", "Open");
        restaurant3.put("durationInfo", "10:00 - 22:00");
        restaurant3.put("imgURL", "https://resizer.otstatic.com/v2/photos/xlarge/2/48899571.webp");



        // JSonArray
        JSONArray topRestaurants = new JSONArray();
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);

        return ResponseEntity.ok().body(topRestaurants.toString());
    }
}