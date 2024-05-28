package com.github.mohajel.IE.CA7.models;

import com.github.mohajel.IE.CA7.utils.*;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    public String userName;
    public String password;
    public String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address address;

    public int numberOfReservations;

    @Enumerated(EnumType.STRING)
    public Role role;
    public enum Role {
        CLIENT,
        MANAGER
    }

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

    // Empty constructor for Hibernate
    public User() {

    }
}
