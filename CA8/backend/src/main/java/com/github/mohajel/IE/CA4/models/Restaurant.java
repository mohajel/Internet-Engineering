package com.github.mohajel.IE.CA4.models;

import com.github.mohajel.IE.CA4.utils.Utils;
import org.json.JSONObject;

import com.github.mohajel.IE.CA4.utils.MizdooniError;

public class Restaurant {

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

    public static int OverID = 0;

    public String name;
    public int id;
    public String managerUserName;
    public String type;
    public Hour startTime;
    public Hour endTime;
    public String description;
    public Address address;
    public String pictureAddress;

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
