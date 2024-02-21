package com.github.mohajel.IE.CA1.models;

import com.github.mohajel.IE.CA1.utils.*;

public class User {

    public User(String userName, String password, String email, Address address, String role) throws MizdooniError {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.numberOfReservations = 0;
        if (role.equals("client")) {
            this.role = Role.CLIENT;
        } else if (role.equals("manager")) {
            this.role = Role.MANAGER;
        } else {
            throw new MizdooniError(MizdooniError.INVALID_ROLE);
        }
    }

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
