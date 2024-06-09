package com.github.mohajel.IE.CA8.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.json.JSONObject;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String street;
    public String city;
    public String country;

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

    // Empty constructor for Hibernate
    public Address() {

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("country", this.country);
        json.put("city", this.city);
        json.put("street", this.street);
        return json;
    }
}
