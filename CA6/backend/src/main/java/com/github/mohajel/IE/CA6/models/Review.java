package com.github.mohajel.IE.CA6.models;

import org.json.JSONObject;

import com.github.mohajel.IE.CA6.utils.MizdooniError;

public class Review {

    public Review(String userName, String restaurantName, double foodRate, double serviceRate,
            double ambianceRate, double overallRate, String comment, MizdooniDate reviewDate) throws MizdooniError {
                
        this.userName = userName;
        this.restaurantName = restaurantName;
        this.foodRate = foodRate;
        this.serviceRate = serviceRate;
        this.ambianceRate = ambianceRate;
        this.overallRate = overallRate;
        this.comment = comment;
        this.reviewDate = reviewDate;

        if (!isValidRating(foodRate) || !isValidRating(serviceRate) ||
                !isValidRating(ambianceRate) || !isValidRating(overallRate)) {
            throw new MizdooniError(MizdooniError.INVALID_RATING);
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", this.userName);
        json.put("restaurantName", this.restaurantName);
        json.put("foodRate", this.foodRate);
        json.put("serviceRate", this.serviceRate);
        json.put("ambianceRate", this.ambianceRate);
        json.put("overallRate", this.overallRate);
        json.put("comment", this.comment);
        json.put("reviewDate", this.reviewDate.getDateTime());
        return json;
    }

    private boolean isValidRating(double rate) {
        return rate >= 0 && rate <= 5;
    }

    public String userName;
    public String restaurantName;
    public double foodRate;
    public double serviceRate;
    public double ambianceRate;
    public double overallRate;
    public String comment;
    public MizdooniDate reviewDate;
}
