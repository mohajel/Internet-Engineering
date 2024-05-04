package com.github.mohajel.IE.CA4.controllers;

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
        // TODO: Implement this method
        String body = """
                [
                    {
                        id: 1,
                        capacity: 4
                    },
                    {
                        id: 2,
                        capacity: 2
                    },
                    {
                        id: 3,
                        capacity: 6
                    },
                    {
                        id: 4,
                        capacity: 8
                    },
                    {
                        id: 5,
                        capacity: 4
                    },
                    {
                        id: 6,
                        capacity: 2
                    },
                    {
                        id: 7,
                        capacity: 6
                    },
                    {
                        id: 8,
                        capacity: 8
                    },
                    {
                        id: 9,
                        capacity: 4
                    },
                    {
                        id: 10,
                        capacity: 2
                    },
                    {
                        id: 11,
                        capacity: 6
                    },
                    {
                        id: 12,
                        capacity: 8
                    },
                    {
                        id: 13,
                        capacity: 4
                    },
                    {
                        id: 14,
                        capacity: 2
                    }
                ]""";

        return ResponseEntity.ok().body(body);
    }
}
