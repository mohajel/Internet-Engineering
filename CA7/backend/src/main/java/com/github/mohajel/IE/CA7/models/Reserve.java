package com.github.mohajel.IE.CA7.models;

import jakarta.persistence.*;
import org.json.JSONObject;

@Entity
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_database;

    public String userName;
    public String restaurantName;
    public int tableId;
    public int reservationId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reserveDate_id", referencedColumnName = "id")
    public MizdooniDate reserveDate;

    public boolean isCancelled;

    public Reserve(String userName, String restaurantName, int tableId, MizdooniDate reserveDate) {
        this.userName = userName;
        this.restaurantName = restaurantName;
        this.tableId = tableId;
        this.reserveDate = reserveDate;
        this.isCancelled = false;
    }

    // Empty constructor for JPA
    public Reserve() {

    }

    public JSONObject toJson() {
        return new JSONObject().put("reservationNumber", this.reservationId).put("restaurantName", this.restaurantName)
                .put("tableNumber", this.tableId).put("datetime", this.reserveDate.toString());
    }
}
