package com.github.mohajel.IE.CA1.database;

import java.util.ArrayList;

import com.github.mohajel.IE.CA1.models.*;
import com.github.mohajel.IE.CA1.utils.MizdooniError;
import com.github.mohajel.IE.CA1.utils.Utils;

public class Database {

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Reserve> reserves;
    private ArrayList<Table> tables;
    private ArrayList<User> users;
    private ArrayList<Review> reviews;

    public Database() {
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
            throw new MizdooniError(MizdooniError.TABLEID_IN_RESTAURANT_DOES_NOT_EXIST);
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

        this.reviews.add(review);
    }

    public void cancelReservation(String userName, int reservationId) throws MizdooniError {
        Reserve reserve = this.getReserveByReservationIdAndUserName(reservationId, userName);
        if (reserve == null) {
            throw new MizdooniError(MizdooniError.RESERVATION_DOES_NOT_EXIST);
        }
        MizdooniDate currentTime = new MizdooniDate(Utils.getCurrentTime());
        if (reserve.reserveDate.isBefore(currentTime)) {
            throw new MizdooniError(MizdooniError.DATETIME_IS_PASSED);
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

    public Restaurant getRestaurantByName(String restaurantName) {
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.name.equals(restaurantName)) {
                return restaurant;
            }
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurantByType(String restaurantType) {
        ArrayList<Restaurant> restaurantsByType = new ArrayList<Restaurant>();
        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.type.equals(restaurantType)) {
                restaurantsByType.add(restaurant);
            }
        }
        return restaurantsByType;
    }

    public Table getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        for (Table table : this.tables) {
            if (table.id == tableId && table.restaurantName.equals(restaurantName)) {
                return table;
            }
        }
        return null;
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

    public Reserve getReserveByUserNameAndRestaurantNameAndTableId(String userName, String restaurantName,
            int tableId) {
        for (Reserve reserve : this.reserves) {
            if (reserve.userName.equals(userName) && reserve.restaurantName.equals(restaurantName)
                    && reserve.tableId == tableId) {
                return reserve;
            }
        }
        return null;
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

    public ArrayList<Reserve> getReservesByRestaurantName(String restaurantName) {
        ArrayList<Reserve> restaurantReserves = new ArrayList<Reserve>();
        for (Reserve reserve : this.reserves) {
            if (reserve.restaurantName.equals(restaurantName)) {
                restaurantReserves.add(reserve);
            }
        }
        return restaurantReserves;
    }

    public Reserve getReserveByReservationIdAndUserName(int reservationId, String userName) {
        for (Reserve reserve : this.reserves) {
            if (reserve.reservationId == reservationId && reserve.userName.equals(userName)) {
                return reserve;
            }
        }
        return null;
    }
}
