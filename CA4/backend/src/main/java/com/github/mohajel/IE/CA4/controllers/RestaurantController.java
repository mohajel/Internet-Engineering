package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class RestaurantController {

    Logger logger = LoggerFactory.getLogger(RestaurantController.class);


    @RequestMapping(value = "/topRestaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTopRestaurants() {
        // numberOfStars = props.numberOfStars;
        // restaurantName = props.restaurantName;
        // reviewCount = props.reviewCount;
        // restaurantType = props.restaurantType;
        // location = props.location;
        // openStatus = props.openStatus;
        // durationInfo = props.durationInfo;
        
        JSONObject restaurant1 = new JSONObject();
        restaurant1.put("numberOfStars", 5);
        restaurant1.put("restaurantName", "KFC");
        restaurant1.put("reviewCount", 100);
        restaurant1.put("restaurantType", "Fast Food");
        restaurant1.put("location", "Tehran");
        restaurant1.put("openStatus", "Open");
        restaurant1.put("durationInfo", "10:00 - 22:00");

        JSONObject restaurant2 = new JSONObject();
        restaurant2.put("numberOfStars", 4);
        restaurant2.put("restaurantName", "McDonalds");
        restaurant2.put("reviewCount", 200);
        restaurant2.put("restaurantType", "Fast Food");
        restaurant2.put("location", "Tehran");
        restaurant2.put("openStatus", "Open");
        restaurant2.put("durationInfo", "10:00 - 22:00");

        JSONObject restaurant3 = new JSONObject();
        restaurant3.put("numberOfStars", 3);
        restaurant3.put("restaurantName", "Pizza Hut");
        restaurant3.put("reviewCount", 300);
        restaurant3.put("restaurantType", "Fast Food");
        restaurant3.put("location", "Tehran");
        restaurant3.put("openStatus", "Open");
        restaurant3.put("durationInfo", "10:00 - 22:00");


        // JSonArray
        JSONArray topRestaurants = new JSONArray();
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);
        topRestaurants.put(restaurant1);
        topRestaurants.put(restaurant2);
        topRestaurants.put(restaurant3);

        return topRestaurants.toString();
    }

    @RequestMapping(value = "/suggestedRestaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSuggestedRestaurants() {
        // numberOfStars = props.numberOfStars;
        // restaurantName = props.restaurantName;
        // reviewCount = props.reviewCount;
        // restaurantType = props.restaurantType;
        // location = props.location;
        // openStatus = props.openStatus;
        // durationInfo = props.durationInfo;
        
        JSONObject restaurant1 = new JSONObject();
        restaurant1.put("numberOfStars", 5);
        restaurant1.put("restaurantName", "KFC");
        restaurant1.put("reviewCount", 100);
        restaurant1.put("restaurantType", "Fast Food");
        restaurant1.put("location", "Tehran");
        restaurant1.put("openStatus", "Open");
        restaurant1.put("durationInfo", "10:00 - 22:00");

        JSONObject restaurant2 = new JSONObject();
        restaurant2.put("numberOfStars", 4);
        restaurant2.put("restaurantName", "McDonalds");
        restaurant2.put("reviewCount", 200);
        restaurant2.put("restaurantType", "Fast Food");
        restaurant2.put("location", "Tehran");
        restaurant2.put("openStatus", "Open");
        restaurant2.put("durationInfo", "10:00 - 22:00");

        JSONObject restaurant3 = new JSONObject();
        restaurant3.put("numberOfStars", 3);
        restaurant3.put("restaurantName", "Pizza Hut");
        restaurant3.put("reviewCount", 300);
        restaurant3.put("restaurantType", "Fast Food");
        restaurant3.put("location", "Tehran");
        restaurant3.put("openStatus", "Open");
        restaurant3.put("durationInfo", "10:00 - 22:00");


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

        return topRestaurants.toString();
    }
}