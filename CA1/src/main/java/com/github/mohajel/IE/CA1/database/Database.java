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
        } else if (! Utils.isEmail(user.email)) {
            throw new MizdooniError(MizdooniError.INVALID_EMAIL_FORMAT);
        }

        this.users.add(user);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

    public void reserveTable(Reserve reserve) {
        this.reserves.add(reserve);
    }

    public void addReviews(Review review) {
        this.reviews.add(review);
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

    public Table getTableByIdAndRestaurantName(int tableId, String restaurantName) {
        for (Table table : this.tables) {
            if (table.id == tableId && table.restaurantName.equals(restaurantName)) {
                return table;
            }
        }
        return null;
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

    public ArrayList<Reserve> getReservesByUserName(String userName) {
        ArrayList<Reserve> userReserves = new ArrayList<Reserve>();
        for (Reserve reserve : this.reserves) {
            if (reserve.userName.equals(userName)) {
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

}
