package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @GetMapping("/{restaurantName}")
    public ResponseEntity<String> getReviewsForRestaurant(String restaurantName) {
        String reviews = """
                [
                    {
                        "ambianceRate": 4,
                        "comment": "Enjoyed a pleasant dinner at Sullivan's. The steak was cooked perfectly, and the service was attentive. The ambiance was nice and cozy. Overall, a good experience.",
                        "foodRate": 4,
                        "overallRate": 4,
                        "restaurantName": "Sullivan's Steakhouse",
                        "serviceRate": 3,
                        "username": "MohammadMehdi_Jafari"
                    },
                    {
                        "ambianceRate": 5,
                        "comment": "Eddie Merlot's exceeded expectations! The steak was incredible, and the service was top-notch. The ambiance was perfect for a special evening out.",
                        "foodRate": 5,
                        "overallRate": 5,
                        "restaurantName": "Eddie Merlot's Prime Aged Beef & Seafood",
                        "serviceRate": 4,
                        "username": "Kasri_HajiHeidari"
                    },
                    {
                        "ambianceRate": 4,
                        "comment": "Had a lovely time at The Winery. The food was delicious, and the wine selection was impressive. Excellent service added to the overall enjoyment.",
                        "foodRate": 4,
                        "overallRate": 4,
                        "restaurantName": "The Winery Restaurant & Wine Bar",
                        "serviceRate": 5,
                        "username": "Marzieh_Hariri"
                    }
                ]""";

        return ResponseEntity.ok().body(reviews);
    }
}
