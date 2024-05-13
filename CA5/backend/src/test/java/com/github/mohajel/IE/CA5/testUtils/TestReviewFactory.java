package com.github.mohajel.IE.CA5.testUtils;

import org.json.JSONObject;

import com.github.mohajel.IE.CA5.utils.Utils;

public class TestReviewFactory {

    public static JSONObject createSimpleReview(String userName, String restaurantName,
            double foodRate) {

        double serviceRate = 1.2;
        double ambianceRate = 3.4;
        double overallRate = 2.3;
        String comment = "This is a comment";

        JSONObject review = new JSONObject();
        review.put("username", userName);
        review.put("restaurantName", restaurantName);
        review.put("foodRate", foodRate);
        review.put("serviceRate", serviceRate);
        review.put("ambianceRate", ambianceRate);
        review.put("overallRate", overallRate);
        review.put("comment", comment);
        review.put("reviewDate", Utils.getCurrentTime());
        return review;
    }
}
