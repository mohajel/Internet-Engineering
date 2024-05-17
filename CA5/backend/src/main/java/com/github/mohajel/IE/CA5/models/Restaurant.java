package com.github.mohajel.IE.CA5.models;

import com.github.mohajel.IE.CA5.utils.Utils;
import jakarta.persistence.*;
import org.json.JSONObject;

import com.github.mohajel.IE.CA5.utils.MizdooniError;

@Entity
public class Restaurant {

    public static int OverID = 0;

    @Id
    public String name;
    public int id;
    public String managerUserName;
    public String type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "startTime_id", referencedColumnName = "id")
    public Hour startTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endTime_id", referencedColumnName = "id")
    public Hour endTime;

    public String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address address;

    public String pictureAddress;

    public Restaurant(String name, String managerUserName, String type, Hour startTime, Hour endTime, String description, Address address, String pictureAddress) throws MizdooniError {
        this.name = name;
        OverID++;
        this.id = OverID;
        this.managerUserName = managerUserName;
        this.type = type;

        if (!(startTime.isHourRounded() && endTime.isHourRounded())) {
            throw new MizdooniError(MizdooniError.HOUR_IS_NOT_ROUND);
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.address = address;
        this.pictureAddress = pictureAddress;
    }


    // Empty constructor for Hibernate
    public Restaurant() {

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("id", this.id);
        json.put("type", this.type);
        json.put("startTime", this.startTime.toString());
        json.put("endTime", this.endTime.toString());
        json.put("description", this.description);
        json.put("address", this.address.toJson());
        json.put("image", this.pictureAddress);
        json.put("managerUsername", this.managerUserName);

        return json;
    }

    public boolean isOpen() {
        Hour now = new MizdooniDate(Utils.getCurrentTime()).getTime();
        return now.isTimeInRange(this.startTime, this.endTime);
    }
}
