package com.github.mohajel.IE.CA4.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.mohajel.IE.CA4.models.*;
import com.github.mohajel.IE.CA4.utils.MizdooniError;
import com.github.mohajel.IE.CA4.utils.Utils;

public class Database {

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Reserve> reserves;
    private ArrayList<Table> tables;
    private ArrayList<User> users;
    private ArrayList<Review> reviews;

    public Database() throws MizdooniError{
        this.restaurants = new ArrayList<Restaurant>();
        this.reserves = new ArrayList<Reserve>();
        this.tables = new ArrayList<Table>();
        this.users = new ArrayList<User>();
        this.reviews = new ArrayList<Review>();
    }

    public void addUser(User user) throws MizdooniError {

        if (this.getUserByUserName(user.userName) != null) {
            throw new MizdooniError(MizdooniError.USER_ALREADY_EXISTS);
        } else if (this.getUserByEmail(user.email) != null) {
            throw new MizdooniError(MizdooniError.EMAIL_ALREADY_EXISTS);
        } else if (Utils.containsCharacters(user.userName)) {
            throw new MizdooniError(MizdooniError.INVALID_USERNAME_CONTAINS_SPECIAL_CHARACTERS);
        } else if (!Utils.isEmail(user.email)) {
            throw new MizdooniError(MizdooniError.INVALID_EMAIL_FORMAT);
        }

        this.users.add(user);
    }

public void addRestaurant(Restaurant restaurant) throws MizdooniError {

        if (this.getRestaurantByName(restaurant.name) != null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_ALREADY_EXISTS);
        }

        User manager = this.getUserByUserName(restaurant.managerUserName);
        if (manager == null) {
            throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
        } else if (manager.role != User.Role.MANAGER) {
            throw new MizdooniError(MizdooniError.USER_IS_NOT_MANAGER);
        }

        this.restaurants.add(restaurant);
    }

    public void addTable(Table table) throws MizdooniError {
        // tableNumber is unique for each restaurant
        for (Table t : this.tables) {
            if (t.id == table.id && t.restaurantName.equals(table.restaurantName)) {
                throw new MizdooniError(MizdooniError.TABLE_ID_NOT_UNIQUE);
            }
        }
        // managerUserName is the manager of the restaurant
        User manager = this.getUserByUserName(table.managerUserName);
        // restaurant name should exist
        Restaurant restaurant = this.getRestaurantByName(table.restaurantName);
        if (restaurant == null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
        }

        if (manager == null) {
            throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
        } else if (manager.role != User.Role.MANAGER) {
            throw new MizdooniError(MizdooniError.USER_IS_NOT_MANAGER);
        }

        this.tables.add(table);
    }

    public void reserveTable(Reserve reserve) throws MizdooniError {
        User user = this.getUserByUserName(reserve.userName);
        if (user == null) {
            throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
        }
        if (user.role == User.Role.MANAGER) {
            throw new MizdooniError(MizdooniError.USER_IS_MANAGER);
        }
        if (!reserve.reserveDate.isHourRounded()) {
            throw new MizdooniError(MizdooniError.HOUR_IS_NOT_ROUND);
        }

        Restaurant restaurant = this.getRestaurantByName(reserve.restaurantName);
        if (restaurant == null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
        }

        Table table = this.getTableByIdAndRestaurantName(reserve.tableId, reserve.restaurantName);
        if (table == null) {
            throw new MizdooniError(MizdooniError.TABLE_ID_IN_RESTAURANT_DOES_NOT_EXIST);
        }
        if (this.isTableReserved(reserve.tableId, reserve.restaurantName, reserve.reserveDate)) {
            throw new MizdooniError(MizdooniError.TABLE_IS_RESERVED);
        }
        MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());
        if (reserve.reserveDate.isBefore(currentTime)) {
            throw new MizdooniError(MizdooniError.DATETIME_IS_PASSED);
        }

        if (!reserve.reserveDate.isHourInRange(restaurant.startTime, restaurant.endTime)) {
            throw new MizdooniError(MizdooniError.DATETIME_IS_NOT_IN_OPEN_HOURS);
        }
        user.numberOfReservations++;
        reserve.reservationId = user.numberOfReservations;

        this.reserves.add(reserve);
    }

    public JSONObject showAvailableTablesToday(String restaurantName) throws MizdooniError {
        Restaurant restaurant = this.getRestaurantByName(restaurantName);
        if (restaurant == null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
        }

        MizdooniDate today = new MizdooniDate(Utils.getCurrentTime());
        JSONObject availableTablesToday = new JSONObject();
        availableTablesToday.put("availableTables", new JSONArray());
        for (Table table : this.tables) {
            if (table.restaurantName.equals(restaurantName)) {
                JSONObject tableJson = new JSONObject();
                tableJson.put("tableNumber", table.id);
                tableJson.put("seatsNumber", table.capacity);
                tableJson.put("availableTimes", new JSONArray());

                ArrayList <Integer> reservedHours = new ArrayList<Integer>();
                for (Reserve reserve : this.reserves) {
                    if (reserve.restaurantName.equals(restaurantName) && reserve.tableId == table.id
                            && reserve.reserveDate.isNDaysAfter(today, 0) && !reserve.isCancelled) {
                        reservedHours.add(reserve.reserveDate.getTime().getJustHours());
                    }
                }

                for (int i = today.getTime().getJustHours(); i < 24; i++) {
                    Hour hour = new Hour(String.format("%02d:00", i));
                    if (!reservedHours.contains(i) && hour.isTimeInRange(restaurant.startTime, restaurant.endTime)) {
                        tableJson.getJSONArray("availableTimes").put(today.getDateTime() + " " + String.format("%02d:00", i));
                    }
                }
                availableTablesToday.getJSONArray("availableTables").put(tableJson);
            }
        }
        return availableTablesToday;
    }

    /// Reviews
    public void addReview(Review review) throws MizdooniError {
        // user must exist and must not be manager
        User user = this.getUserByUserName(review.userName);
        if (user == null) {
            throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
        } else if (user.role == User.Role.MANAGER) {
            throw new MizdooniError(MizdooniError.USER_IS_MANAGER);
        }
        // restaurant must exist
        Restaurant restaurant = this.getRestaurantByName(review.restaurantName);
        if (restaurant == null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
        }

        Review oldReview = this.getReviewByUserNameAndRestaurantName(review.userName, review.restaurantName);

        if (oldReview == null) {
            this.reviews.add(review);
        } else {
            oldReview.foodRate = review.foodRate;
            oldReview.serviceRate = review.serviceRate;
            oldReview.ambianceRate = review.ambianceRate;
            oldReview.overallRate = review.overallRate;
            oldReview.comment = review.comment;
            oldReview.reviewDate = review.reviewDate;
        }

    }

//    i wanna check user have a reservation that is not cancelled and its date is before current time
    public boolean isUserHasReservationNotCancelled(String userName, String restaurantName) {
        MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());
        for (Reserve reserve : this.reserves) {
            if (reserve.userName.equals(userName) &&
                    reserve.restaurantName.equals(restaurantName) &&
                    !reserve.isCancelled &&
                    reserve.reserveDate.isBefore(currentTime)) {
                return true;
            }
        }
        return false;
    }

    public JSONArray getReviewsByRestaurantName(String restaurantName) {
        JSONArray reviewsByRestaurant = new JSONArray();
        for (Review review : this.reviews) {
            if (review.restaurantName.equals(restaurantName)) {
                reviewsByRestaurant.put(review.toJson());
            }
        }
        return reviewsByRestaurant;
    }

    private Review getReviewByUserNameAndRestaurantName(String userName, String restaurantName) {
        for (Review review : this.reviews) {
            if (review.userName.equals(userName) && review.restaurantName.equals(restaurantName)) {
                return review;
            }
        }
        return null;
    }

    public JSONArray getAllRestaurantCards() {
        JSONArray result = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            result.put(this.restaurantConvert2restaurantCard(restaurant));
        }
        return result;
    }

    public JSONArray getAllRestaurantWithAVGRate() {
        JSONArray result = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            JSONObject restaurantJson = restaurant.toJson();
            restaurantJson.put("rate", this.getAVGRateRestaurantByName(restaurant.name));
            result.put(restaurantJson);
        }
        return result;
    }

    public JSONObject getAVGRateRestaurantByName(String nameRestaurant) {
        double sumFoodRate = 0;
        double sumServiceRate = 0;
        double sumAmbianceRate = 0;
        double sumOverallRate = 0;

        int count = 0;

        for (Review review : this.reviews) {
            if (review.restaurantName.equals(nameRestaurant)) {
                sumFoodRate += review.foodRate;
                sumServiceRate += review.serviceRate;
                sumAmbianceRate += review.ambianceRate;
                sumOverallRate += review.overallRate;
                count++;
            }
        }

        JSONObject result = new JSONObject();

        if (count == 0) {
            result.put("foodRate", 0);
            result.put("serviceRate", 0);
            result.put("ambianceRate", 0);
            result.put("overallRate", 0);
            return result;
        }

        result.put("reviewsCount", count);
        result.put("foodRate", sumFoodRate / count);
        result.put("serviceRate", sumServiceRate / count);
        result.put("ambianceRate", sumAmbianceRate / count);
        result.put("overallRate", sumOverallRate / count);

        return result;
    }

    public void cancelReservation(String userName, int reservationId) throws MizdooniError {
        Reserve reserve = this.getReserveByReservationIdAndUserName(reservationId, userName);
        if (reserve == null) {
            throw new MizdooniError(MizdooniError.RESERVATION_DOES_NOT_EXIST);
        }
        MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());
        if (reserve.reserveDate.isBefore(currentTime)) {
            throw new MizdooniError(MizdooniError.RESERVATION_TIME_PASSED);
        }
        this.reserves.remove(reserve);
    }

    public User getUserByUserName(String userName) {
        for (User user : this.users) {
            if (user.userName.equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : this.users) {
            if (user.email.equals(email)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurantsContainName(String restaurantName) {
        ArrayList<Restaurant> restaurantsContainName = new ArrayList<Restaurant>();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.name.contains(restaurantName)) {
                restaurantsContainName.add(restaurant);
            }
        }
        return restaurantsContainName;
    }

    public JSONArray getJSONRestaurantsContainName(String restaurantName) {
        JSONArray restaurantsContainName = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.name.contains(restaurantName)) {
                JSONObject restaurantJson = restaurant.toJson();
                restaurantJson.put("rate", this.getAVGRateRestaurantByName(restaurant.name));
                restaurantsContainName.put(restaurantJson);
            }
        }
        return restaurantsContainName;
    }

    /// Search for restaurants
    public Restaurant getRestaurantByName(String restaurantName) {
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.name.equals(restaurantName)) {
                return restaurant;
            }
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurantsByType(String restaurantType) {
        ArrayList<Restaurant> restaurantsByType = new ArrayList<Restaurant>();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.type.equals(restaurantType)) {
                restaurantsByType.add(restaurant);
            }
        }
        return restaurantsByType;
    }

    public JSONArray getRestaurantByType(String restaurantType) {
        JSONArray restaurantsByType = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.type.equals(restaurantType)) {
                JSONObject restaurantJson = restaurant.toJson();
                restaurantJson.put("rate", this.getAVGRateRestaurantByName(restaurant.name));
                restaurantsByType.put(restaurantJson);
            }
        }
        return restaurantsByType;
    }

    public JSONArray getRestaurantCardsByCity(String restaurantLocation) {
        JSONArray restaurantsByCity = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.address.city.equals(restaurantLocation)) {
                restaurantsByCity.put(this.restaurantConvert2restaurantCard(restaurant));
            }
        }
        return restaurantsByCity;
    }

    public JSONArray getRestaurantByCity(String restaurantLocation) {
        JSONArray restaurantsByCity = new JSONArray();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.address.city.equals(restaurantLocation)) {
                JSONObject restaurantJson = restaurant.toJson();
                restaurantJson.put("rate", this.getAVGRateRestaurantByName(restaurant.name));
                restaurantsByCity.put(restaurantJson);
            }
        }
        return restaurantsByCity;
    }

    public Table getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        for (Table table : this.tables) {
            if (table.id == tableId && table.restaurantName.equals(restaurantName)) {
                return table;
            }
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurantsByManagerUserName(String managerUserName) {
        ArrayList<Restaurant> restaurantsByManager = new ArrayList<Restaurant>();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.managerUserName.equals(managerUserName)) {
                restaurantsByManager.add(restaurant);
            }
        }
        return restaurantsByManager;
    }

    public boolean isTableReserved(int tableId, String restaurantName, MizdooniDate reserveDate) {
        for (Reserve reserve : this.reserves) {
            if (reserve.tableId == tableId && reserve.restaurantName.equals(restaurantName)
                    && reserve.reserveDate.equals(reserveDate) && !reserve.isCancelled) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Table> getTablesByRestaurantName(String restaurantName) {
        ArrayList<Table> tablesByRestaurant = new ArrayList<Table>();
        for (Table table : this.tables) {
            if (table.restaurantName.equals(restaurantName)) {
                tablesByRestaurant.add(table);
            }
        }
        return tablesByRestaurant;
    }

    public ArrayList<Table> getTablesByManagerName(String managerUserName) {
        ArrayList<Table> tablesByManager = new ArrayList<Table>();
        for (Table table : this.tables) {
            if (table.managerUserName.equals(managerUserName)) {
                tablesByManager.add(table);
            }
        }
        return tablesByManager;
    }

    public ArrayList<Reserve> getReservationsByUserName(String userName) {
        ArrayList<Reserve> userReserves = new ArrayList<Reserve>();
        for (Reserve reserve : this.reserves) {
            if (reserve.userName.equals(userName) && !reserve.isCancelled) {
                userReserves.add(reserve);
            }
        }
        return userReserves;
    }

    public Reserve getReserveByReservationIdAndUserName(int reservationId, String userName) {
        for (Reserve reserve : this.reserves) {
            if (reserve.reservationId == reservationId && reserve.userName.equals(userName)) {
                return reserve;
            }
        }
        return null;
    }

    public JSONObject restaurantConvert2restaurantCard(Restaurant restaurant) {
        JSONObject restaurantCard = new JSONObject();
        restaurantCard.put("imgURL", restaurant.pictureAddress);
        JSONObject rates = this.getAVGRateRestaurantByName(restaurant.name);
        restaurantCard.put("numberOfStars", rates.getDouble("overallRate"));
        restaurantCard.put("openStatus", restaurant.isOpen() ? "Open" : "Closed");
        restaurantCard.put("restaurantName", restaurant.name);
        restaurantCard.put("reviewCount", this.getReviewsByRestaurantName(restaurant.name).length());
        restaurantCard.put("restaurantType", restaurant.type);
        restaurantCard.put("location", restaurant.address.city);
        restaurantCard.put("id", restaurant.id);
        restaurantCard.put("durationInfo", restaurant.startTime.toString() + " - " + restaurant.endTime.toString());

        return restaurantCard;
    }
}
