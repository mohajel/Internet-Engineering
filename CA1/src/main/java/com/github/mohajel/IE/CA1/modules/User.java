package com.github.mohajel.IE.CA1.modules;

public class User {

    public String userName;
    public String password;
    public String email;
    public Address address;
    public int numberOfReservations;
    public Role role;

    public enum Role {
        CLIENT,
        MANAGER
    }
}
