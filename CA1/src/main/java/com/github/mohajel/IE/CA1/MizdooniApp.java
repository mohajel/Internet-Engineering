package com.github.mohajel.IE.CA1;

import org.json.*;

import com.github.mohajel.IE.CA1.database.Database;
import com.github.mohajel.IE.CA1.models.*;
import com.github.mohajel.IE.CA1.utils.MizdooniError;
import com.github.mohajel.IE.CA1.utils.Utils;

import java.util.ArrayList;

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

            User user = new User(username, password, email,
                    new Address(address.getString("country"), address.getString("city")), role);
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
        System.out.println("add restaurant called");
        JSONObject output = new JSONObject();
        try {
            String name = input.getString("name");
            String managerUsername = input.getString("managerUsername");
            String type = input.getString("type");
            String startTime = input.getString("startTime");
            String endTime = input.getString("endTime");
            String description = input.getString("description");
            JSONObject address = input.getJSONObject("address");

            Restaurant restaurant = new Restaurant(name, managerUsername, type, new Hour(startTime), new Hour(endTime),
                    description,
                    new Address(address.getString("street"), address.getString("city"), address.getString("country")));
            db.addRestaurant(restaurant);
            output.put("success", true);
            output.put("data", "Restaurant added successfully.");
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

    public JSONObject addTable(JSONObject input) {
        System.out.println("add table called");
        JSONObject output = new JSONObject();
        try {
            int tableNumber = input.getInt("tableNumber");
            String restaurantName = input.getString("restaurantName");
            String managerUsername = input.getString("managerUsername");
            Double seatsNumber = input.getDouble("seatsNumber");
            int seatsNumberInt = seatsNumber.intValue();

            if (seatsNumber % 1 != 0) {
                throw new MizdooniError(MizdooniError.INVALID_JSON_SEATS_NUMBER_NOT_NATURAL);
            } else {
                seatsNumberInt = seatsNumber.intValue();
            }

            Table table = new Table(tableNumber, restaurantName, managerUsername, seatsNumberInt);
            db.addTable(table);
            output.put("success", true);
            output.put("data", "Table added successfully.");
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

    public JSONObject reserveTable(JSONObject input) {
        System.out.println("reserve table called");
        JSONObject output = new JSONObject();
        try {
            String userName = input.getString("username");
            String restaurantName = input.getString("restaurantName");
            int tableNumber = input.getInt("tableNumber");

            if (!MizdooniDate.isDateTimeFormatValid(input.getString("datetime"))) {
                throw new MizdooniError(MizdooniError.DATETIME_FORMAT_INVALID);
            }
            MizdooniDate reserveDate = new MizdooniDate(input.getString("datetime"));

            Reserve reserve = new Reserve(userName, restaurantName, tableNumber, reserveDate);
            db.reserveTable(reserve);
            output.put("success", true);
            output.put("data", new JSONObject().put("reservationNumber", reserve.reservationId));
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

    public JSONObject cancelReservation(JSONObject input) {
        // TODO
        System.out.println("cancel reservation called");
        return input;
    }

    public JSONObject showReservationHistory(JSONObject input) {
        System.out.println("show reservation history called");
        JSONObject output = new JSONObject();
        try {
            String userName = input.getString("username");

            ArrayList<Reserve> reservations = db.getReservationsByUserName(userName);

            output.put("success", true);
            output.put("data", new JSONObject().put("reservationHistory", new JSONArray()));
            for (Reserve reservation : reservations) {
                output.getJSONObject("data").getJSONArray("reservations").put(reservation.toJson());
            }
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKOOWN_ERROR));
        }
        return output;
    }

    public JSONObject searchRestaurantsByName(JSONObject data) {
        System.out.println("search restaurants by name called");
        JSONObject output = new JSONObject();
        try {
            String name = data.getString("name");

            Restaurant restaurant = db.getRestaurantByName(name);
            if (restaurant == null) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }

            output.put("success", true);
            output.put("data", restaurant.toJson());
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

    public JSONObject searchRestaurantsByType(JSONObject input) {
        System.out.println("search restaurants by type called");
        JSONObject output = new JSONObject();
        try {
            String type = input.getString("type");

            ArrayList<Restaurant> restaurants = db.getRestaurantByType(type);
            if (restaurants.isEmpty()) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }

            output.put("success", true);
            output.put("data", new JSONObject().put("restaurants", new JSONArray()));
            for (Restaurant restaurant : restaurants) {
                output.getJSONObject("data").getJSONArray("restaurants").put(restaurant.toJson());
            }
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

    public JSONObject showAvailableTables(JSONObject input) {
        // TODO
        System.out.println("show available tables called");
        return input;
    }

    public JSONObject addReview(JSONObject input) {
        System.out.println("add review called");
        JSONObject output = new JSONObject();

        try {
            String userName = input.getString("userName");
            String restaurantName = input.getString("restaurantName");
            double foodRate = input.getDouble("foodRate");
            double serviceRate = input.getDouble("serviceRate");
            double ambianceRate = input.getDouble("ambianceRate");
            double overallRate = input.getDouble("overallRate");
            String comment = input.getString("comment");
            MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());

            Review review = new Review(userName, restaurantName, foodRate,
                    serviceRate, ambianceRate, overallRate, comment, currentTime);
            db.addReview(review);

            output.put("success", true);
            output.put("data", new JSONObject().put("message", "Review added successfully."));

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

}
