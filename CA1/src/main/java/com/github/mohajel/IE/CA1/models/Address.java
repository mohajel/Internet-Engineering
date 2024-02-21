package com.github.mohajel.IE.CA1.models;

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
}
