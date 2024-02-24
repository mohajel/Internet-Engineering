package com.github.mohajel.IE.CA1.models;

import org.json.JSONObject;

public class Address {

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
        this.street = "";
    }

    public String street;
    public String city;
    public String country;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("country", this.country);
        json.put("city", this.city);
        json.put("street", this.street);
        return json;
    }
}
