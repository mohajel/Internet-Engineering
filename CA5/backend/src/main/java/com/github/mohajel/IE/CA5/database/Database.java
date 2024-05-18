package com.github.mohajel.IE.CA5.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.mohajel.IE.CA5.models.*;
import com.github.mohajel.IE.CA5.utils.MizdooniError;
import com.github.mohajel.IE.CA5.utils.Utils;

public class Database {

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Reserve> reserves;
    private ArrayList<Review> reviews;

    public Database() throws MizdooniError{
        this.restaurants = new ArrayList<Restaurant>();
        this.reserves = new ArrayList<Reserve>();
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

        UserDAO.addUser(user);
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

        RestaurantDAO.addRestaurant(restaurant);
    }

    public void addTable(Dining_Table diningTable) throws MizdooniError {
        // tableNumber is unique for each restaurant
        Dining_Table temp_Dining_table = this.getTableByIdAndRestaurantName(diningTable.id, diningTable.restaurantName);
        if (temp_Dining_table != null) {
            throw new MizdooniError(MizdooniError.TABLE_ID_NOT_UNIQUE);
        }

        // managerUserName is the manager of the restaurant
        User manager = this.getUserByUserName(diningTable.managerUserName);
        // restaurant name should exist
        Restaurant restaurant = this.getRestaurantByName(diningTable.restaurantName);
        if (restaurant == null) {
            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
        }

        if (manager == null) {
            throw new MizdooniError(MizdooniError.USER_DOES_NOT_EXIST);
        } else if (manager.role != User.Role.MANAGER) {
            throw new MizdooniError(MizdooniError.USER_IS_NOT_MANAGER);
        }

        TableDAO.addTable(diningTable);
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

        Dining_Table diningTable = this.getTableByIdAndRestaurantName(reserve.tableId, reserve.restaurantName);
        if (diningTable == null) {
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

        ReserveDAO.addReserve(reserve);
    }

//    public JSONObject showAvailableTablesToday(String restaurantName) throws MizdooniError {
//        Restaurant restaurant = this.getRestaurantByName(restaurantName);
//        if (restaurant == null) {
//            throw new MizdooniError(MizdooniError.RESTAURANT_DOES_NOT_EXIST);
//        }
//
//        MizdooniDate today = new MizdooniDate(Utils.getCurrentTime());
//        JSONObject availableTablesToday = new JSONObject();
//        availableTablesToday.put("availableTables", new JSONArray());
//        for (Dining_Table diningTable : this.diningTables) {
//            if (diningTable.restaurantName.equals(restaurantName)) {
//                JSONObject tableJson = new JSONObject();
//                tableJson.put("tableNumber", diningTable.id);
//                tableJson.put("seatsNumber", diningTable.capacity);
//                tableJson.put("availableTimes", new JSONArray());
//
//                ArrayList <Integer> reservedHours = new ArrayList<Integer>();
//                for (Reserve reserve : this.reserves) {
//                    if (reserve.restaurantName.equals(restaurantName) && reserve.tableId == diningTable.id
//                            && reserve.reserveDate.isNDaysAfter(today, 0) && !reserve.isCancelled) {
//                        reservedHours.add(reserve.reserveDate.getTime().getJustHours());
//                    }
//                }
//
//                for (int i = today.getTime().getJustHours(); i < 24; i++) {
//                    Hour hour = new Hour(String.format("%02d:00", i));
//                    if (!reservedHours.contains(i) && hour.isTimeInRange(restaurant.startTime, restaurant.endTime)) {
//                        tableJson.getJSONArray("availableTimes").put(today.getDateTime() + " " + String.format("%02d:00", i));
//                    }
//                }
//                availableTablesToday.getJSONArray("availableTables").put(tableJson);
//            }
//        }
//        return availableTablesToday;
//    }

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
            System.out.println("add review called");
            ReviewDAO.addReview(review);
        } else {
            oldReview.foodRate = review.foodRate;
            oldReview.serviceRate = review.serviceRate;
            oldReview.ambianceRate = review.ambianceRate;
            oldReview.overallRate = review.overallRate;
            oldReview.comment = review.comment;
            oldReview.reviewDate = review.reviewDate;

            System.out.println("update review called");
            ReviewDAO.updateReview(oldReview);
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
        ArrayList<Review> reviews = ReviewDAO.getReviewsByRestaurantName(restaurantName);

        JSONArray reviewsByRestaurant = new JSONArray();
        for (Review review : reviews) {
            reviewsByRestaurant.put(review.toJson());
        }
        return reviewsByRestaurant;
    }

    private Review getReviewByUserNameAndRestaurantName(String userName, String restaurantName) {
        return ReviewDAO.getReviewByUserNameAndRestaurantName(userName, restaurantName);
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

        result.put("foodRate", String.format("%.1f", sumFoodRate / count));
        result.put("serviceRate", String.format("%.1f", sumServiceRate / count));
        result.put("ambianceRate", String.format("%.1f", sumAmbianceRate / count));
        result.put("overallRate", String.format("%.1f", sumOverallRate / count));

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
        return UserDAO.findUserByUserName(userName);
    }

    public User getUserByEmail(String email) {
        return UserDAO.findUserByEmail(email);
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
        return RestaurantDAO.findRestaurantByName(restaurantName);
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

    public Dining_Table getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        return TableDAO.getTableByIdAndRestaurantName(tableId, restaurantName);
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
        return ReserveDAO.isTableReserved(tableId, restaurantName, reserveDate);
    }

    public ArrayList<Dining_Table> getTablesByRestaurantName(String restaurantName) {
        return TableDAO.getTablesByRestaurantName(restaurantName);
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

    public ArrayList<Reserve> getReservationsByRestaurantName(String restaurantName) {
        ArrayList<Reserve> restaurantReserves = new ArrayList<Reserve>();
        for (Reserve reserve : this.reserves) {
            if (reserve.restaurantName.equals(restaurantName)) {
                restaurantReserves.add(reserve);
            }
        }
        return restaurantReserves;
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
