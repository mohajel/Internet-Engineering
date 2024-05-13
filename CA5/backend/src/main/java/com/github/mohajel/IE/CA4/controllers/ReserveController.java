package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @GetMapping("{restaurantName}")
    public ResponseEntity<String> getReservesByRestaurantName(@PathVariable String restaurantName) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray reserves = app.getReservesByRestaurantName(restaurantName);
        return ResponseEntity.ok().body(reserves.toString());
//        String body = """
//                [
//                    {
//                        userName: "John Doe",
//                        tableId: 1,
//                        reserveDate: "2024-05-01 12:00"
//                        isCancelled: false
//                    },
//                    {
//                        userName: "Mary White",
//                        tableId: 2,
//                        reserveDate: "2025-02-02 13:00"
//                        isCancelled: true
//                    }
//                ]""";
//        return ResponseEntity.ok().body(body);
    }
}
