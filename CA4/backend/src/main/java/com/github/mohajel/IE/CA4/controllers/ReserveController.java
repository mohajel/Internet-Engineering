package com.github.mohajel.IE.CA4.controllers;

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
        String body = """
                [
                    {
                        userName: "John Doe",
                        tableId: 1,
                        reserveDate: "2024-05-01 12:00"
                    },
                    {
                        userName: "Mary White",
                        tableId: 2,
                        reserveDate: "2025-02-02 13:00"
                    }
                ]""";
        return ResponseEntity.ok().body(body);
    }
}
