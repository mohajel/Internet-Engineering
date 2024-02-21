package com.github.mohajel.IE.CA1;

import org.json.*;

import com.github.mohajel.IE.CA1.database.Database;
import com.github.mohajel.IE.CA1.models.*;
import com.github.mohajel.IE.CA1.utils.MizdooniError;;

class MizdooniApp {

    Database db;

    public MizdooniApp() {
        db = new Database();
    }

    public JSONObject addUser(JSONObject input) {
        System.out.println("add user called");
        JSONObject output = new JSONObject();
        try {
            String username = input.getString("username");
            String password = input.getString("password");
            String email = input.getString("email");
            String role = input.getString("role");
            JSONObject address = input.getJSONObject("address");

            User user = new User(username, password, email, new Address(address.getString("country"), address.getString("city")), role);
            db.addUser(user);
            output.put("success", true);
            output.put("data", "User added successfully.");

        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKOOWN_ERROR));
        }
        return output;
    }

    public JSONObject addRestaurant(JSONObject input) {
        // TODO
        System.out.println("add restaurant called");
        return input;
    }

    public JSONObject addTable(JSONObject input) {
        // TODO
        System.out.println("add table called");
        return input;
    }

    public JSONObject reserveTable(JSONObject input) {
        // TODO
        System.out.println("reserve table called");
        return input;
    }

    public JSONObject cancelReservation(JSONObject input) {
        // TODO
        System.out.println("cancel reservation called");
        return input;
    }

    public JSONObject showReservationHistory(JSONObject input) {
        // TODO
        System.out.println("show reservation history called");
        return input;
    }

    public JSONObject searchRestaurantsByType(JSONObject input) {
        // TODO
        System.out.println("search restaurants by type called");
        return input;
    }

    public JSONObject showAvailableTables(JSONObject input) {
        // TODO
        System.out.println("show available tables called");
        return input;
    }

    public JSONObject addReview(JSONObject input) {
        // TODO
        System.out.println("add review called");
        return input;
    }

}
