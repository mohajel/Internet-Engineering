package com.github.mohajel.IE.CA4.controllers;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
public class TableController {

    @GetMapping("/{restaurantName}")
    public ResponseEntity<String> getTablesByRestaurantName(@PathVariable String restaurantName) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONArray tables = app.getTablesByRestaurantName(restaurantName);
        return ResponseEntity.ok().body(tables.toString());
    }
}
