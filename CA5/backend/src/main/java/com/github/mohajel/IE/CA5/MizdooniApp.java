package com.github.mohajel.IE.CA5;

import com.github.mohajel.IE.CA5.utils.*;

import org.json.*;

import com.github.mohajel.IE.CA5.database.Database;
import com.github.mohajel.IE.CA5.models.*;

import java.util.ArrayList;

public class MizdooniApp {

    public Database db;
    private static MizdooniApp single_instance = null;

    // using this for logged_in_user.change in nex phase
    public String loggedInUser = "";

    public MizdooniApp(){
        try {
            db = new Database();
        } catch (MizdooniError e) {
            e.getMessage();
        }
    }

    public MizdooniApp(Database db){
        this.db = db;
    }

    public static synchronized MizdooniApp getInstance()
    {
        if (single_instance == null) {
            try {
                HibernateDatabaseUtil.createDataBase();

                // __INIT__
                Database db = new Database();
                single_instance = new MizdooniApp(db);

                System.out.println("####### Init Mizdooni From API #######");
                InitMizdooniFromAPI.init(single_instance);
                System.out.println("####### Init Mizdooni From File #######");
                InitMizdooniFromFile.init(single_instance); // for get reservation data

            } catch (Exception e){
                System.err.println(e.getStackTrace());
            }
        }
        return single_instance;
    }

    public String cityOfLoggedInUser() {
        if (loggedInUser.isEmpty()) {
            return "";
        }
        return db.getUserByUserName(loggedInUser).address.city;
    }

    public JSONObject login(JSONObject input) {
        System.out.println("login called");
        JSONObject output = new JSONObject();
        try {
            String username = input.getString("username");
            String password = input.getString("password");

            User user = db.getUserByUserName(username);
            if (user == null) {
                throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
            }
            if (!user.password.equals(password)) {
                throw new MizdooniError(MizdooniError.WRONG_PASSWORD);
            }

            output.put("success", true);
            output.put("data", new JSONObject().put("role", user.role.toString()));
            loggedInUser = username;
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    public JSONArray getTablesByRestaurantName(String restaurantName)
    {
        JSONArray tables = new JSONArray();

        ArrayList<Dining_Table> tablesList = db.getTablesByRestaurantName(restaurantName);
        for (Dining_Table diningTable : tablesList) {
            tables.put(diningTable.toJson());
        }
        return tables;
    }

    public JSONArray getRestaurantByManagerUsername(String managerUsername) {
        JSONArray restaurantJSONArray = new JSONArray();

        ArrayList<Restaurant> restaurants = db.getRestaurantsByManagerUserName(managerUsername);

        for (int i = 0; i < restaurants.size(); i++) {
            JSONObject restaurantJson = new JSONObject();
            restaurantJson.put("name", restaurants.get(i).name);
            restaurantJson.put("address", restaurants.get(i).address.toJson());

            restaurantJSONArray.put(restaurantJson);
        }

        return restaurantJSONArray;
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
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
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
            String pictureAddress = input.getString("image");

            Restaurant restaurant = new Restaurant(name, managerUsername, type, new Hour(startTime), new Hour(endTime),
                    description,
                    new Address(address.getString("street"), address.getString("city"), address.getString("country")),
                    pictureAddress);
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
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
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

            Dining_Table diningTable = new Dining_Table(tableNumber, restaurantName, managerUsername, seatsNumberInt);
            db.addTable(diningTable);
            output.put("success", true);
            output.put("data", "Dining_Table added successfully.");
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
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
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    public JSONArray getReservesByRestaurantName(String restaurantName) {
        System.out.println("get reserves by restaurant name called");
        ArrayList<Reserve> reserves = db.getReservationsByRestaurantName(restaurantName);
        JSONArray reservesJson = new JSONArray();
        for (Reserve reserve : reserves) {
            JSONObject singleReserve = new JSONObject();
            singleReserve.put("userName", reserve.userName);
            singleReserve.put("tableId", reserve.tableId);
            singleReserve.put("reserveDate", reserve.reserveDate.toString());
            singleReserve.put("isCancelled", reserve.isCancelled);

            reservesJson.put(singleReserve);
        }
        return reservesJson;
    }

    public JSONObject cancelReservation(JSONObject input) {
        System.out.println("cancel reservation called");

        JSONObject output = new JSONObject();
        try {
            int reservationNumber = input.getInt("reservationNumber");
            String userName = input.getString("username");
            db.cancelReservation(userName, reservationNumber);
            output.put("success", true);
            output.put("data", new JSONObject().put("message", "Reservation canceled successfully."));
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
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
                output.getJSONObject("data").getJSONArray("reservationHistory").put(reservation.toJson());
            }
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    /// Search restaurants
    public JSONObject restaurantConvert2restaurantCard(Restaurant restaurant) {
        JSONObject restaurantCard = new JSONObject();
        restaurantCard.put("imgURL", restaurant.pictureAddress);
        JSONObject rates = db.getAVGRateRestaurantByName(restaurant.name);
        restaurantCard.put("numberOfStars", rates.getDouble("overallRate"));
        restaurantCard.put("openStatus", restaurant.isOpen() ? "Open" : "Closed");
        restaurantCard.put("restaurantName", restaurant.name);
        restaurantCard.put("reviewCount", db.getReviewsByRestaurantName(restaurant.name).length());
        restaurantCard.put("restaurantType", restaurant.type);
        restaurantCard.put("location", restaurant.address.city);
        restaurantCard.put("id", restaurant.id);
        restaurantCard.put("durationInfo", restaurant.startTime.toString() + " - " + restaurant.endTime.toString());

        return restaurantCard;
    }

    public JSONArray getAllRestaurantCards() {
        return db.getAllRestaurantCards();
    }

    public JSONObject getRestaurantCardByName(String name) {
        try {
            Restaurant restaurant = db.getRestaurantByName(name);
            if (restaurant == null) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }
                return restaurantConvert2restaurantCard(restaurant);
        } catch (MizdooniError e) {
            return new JSONObject();
        }
    }

    public JSONObject getRestaurantByName(String name) {
        System.out.println("get restaurant by name called");
        Restaurant restaurant = db.getRestaurantByName(name);
        if (restaurant == null) {
            return new JSONObject();
        }
        return restaurant.toJson();
    }

    public JSONArray searchRestaurantCardsByCity(String city) {
        System.out.println("search restaurant cards by city called");
        return db.getRestaurantCardsByCity(city);
    }

    public JSONArray getRestaurantCardsContainName(String name) {
        System.out.println("get restaurant cards contain name called");
        ArrayList<Restaurant> restaurants = db.getRestaurantsContainName(name);
        JSONArray restaurantCards = new JSONArray();
        for (Restaurant restaurant : restaurants) {
            restaurantCards.put(restaurantConvert2restaurantCard(restaurant));
        }

        return restaurantCards;
    }

    public JSONObject searchRestaurantsContainName(JSONObject data) {
        System.out.println("search restaurants by name called");
        JSONObject output = new JSONObject();
        try {
            String name = data.getString("name");

            JSONArray restaurants = db.getJSONRestaurantsContainName(name);
            if (restaurants.isEmpty()) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }

            output.put("success", true);
            output.put("data", new JSONObject().put("restaurants", restaurants));
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    public JSONArray getRestaurantCardsByType(String type) {
        System.out.println("get restaurant cards by location called");
        ArrayList<Restaurant> restaurants = db.getRestaurantsByType(type);

        JSONArray restaurantCards = new JSONArray();
        for (Restaurant restaurant : restaurants) {
            restaurantCards.put(restaurantConvert2restaurantCard(restaurant));
        }

        return restaurantCards;
    }

    public JSONObject searchRestaurantsByType(JSONObject input) {
        System.out.println("search restaurants by type called");
        JSONObject output = new JSONObject();
        try {
            String type = input.getString("type");

            ArrayList<Restaurant> restaurants = db.getRestaurantsByType(type);
            JSONArray restaurantsJson = new JSONArray();
            for (Restaurant restaurant : restaurants) {
                restaurantsJson.put(restaurant.toJson());
            }
            if (restaurants.isEmpty()) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }
            output.put("success", true);
            output.put("data", new JSONObject().put("restaurants", restaurants));
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    public JSONObject searchRestaurantsByCity(JSONObject input) {
        System.out.println("search restaurants by city called");
        JSONObject output = new JSONObject();
        try {
            String city = input.getString("city");

            JSONArray restaurants = db.getRestaurantByCity(city);
            if (restaurants.isEmpty()) {
                throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
            }

            output.put("success", true);
            output.put("data", new JSONObject().put("restaurants", restaurants));
        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

//    public JSONObject showAvailableTables(JSONObject input) {
//        System.out.println("show available tables called");
//        JSONObject output = new JSONObject();
//        try {
//            String restaurantName = input.getString("restaurantName");
//            JSONObject availableTablesToday = db.showAvailableTablesToday(restaurantName);
//
//            output.put("success", true);
//            output.put("data", availableTablesToday);
//        } catch (JSONException e) {
//            output.put("success", false);
//            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
//        } catch (MizdooniError e) {
//            output.put("success", false);
//            output.put("data", new JSONObject().put("error", e.getMessage()));
//        } catch (Exception e) {
//            output.put("success", false);
//            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
//        }
//        return output;
//    }

    public JSONArray getReviewsByRestaurantName(String restaurantName) {
        return db.getReviewsByRestaurantName(restaurantName);
    }

    public JSONObject addReview(JSONObject input) {
        JSONObject output = new JSONObject();

        try {
            String userName = input.getString("username");
            String restaurantName = input.getString("restaurantName");
            double foodRate = input.getDouble("foodRate");
            double serviceRate = input.getDouble("serviceRate");
            double ambianceRate = input.getDouble("ambianceRate");
            double overallRate = input.getDouble("overallRate");
            String comment = input.getString("comment");
            MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());

            Review review = new Review(userName, restaurantName, foodRate,
                    serviceRate, ambianceRate, overallRate, comment, currentTime);

//            if (!db.isUserHasReservationNotCancelled(userName, restaurantName)) {
//                throw new MizdooniError(MizdooniError.USER_HAS_NO_DONE_RESERVATION);
//            }

            db.addReview(review);

            output.put("success", true);
            output.put("data", "Review added successfully.");

        } catch (JSONException e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.INVALID_JSON));
        } catch (MizdooniError e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", e.getMessage()));
        } catch (Exception e) {
            output.put("success", false);
            output.put("data", new JSONObject().put("error", MizdooniError.UNKNOWN_ERROR));
        }
        return output;
    }

    public JSONObject getSummaryReviewByRestaurantName(String restaurantName) {
        return db.getAVGRateRestaurantByName(restaurantName);
    }

}
