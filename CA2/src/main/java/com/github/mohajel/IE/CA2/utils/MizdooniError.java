package com.github.mohajel.IE.CA2.utils;

public class MizdooniError extends Exception {

    private String message;

    public MizdooniError(String s) {
        super(s);
        this.message = s;
    }

    public String what() {
        return message;
    }

    // general
    public static final String INVALID_COMMAND = "Invalid command";
    public static final String INVALID_JSON = "Invalid input format";
    public static final String UNKNOWN_ERROR = "Unknown error occurred";
    public static final String HOUR_IS_NOT_ROUND = "Hour is not round";
    public static final String USER_DOES_NOT_EXIST = "User does not exist";
    public static final String USER_IS_NOT_MANAGER = "User is not a manager";

    // login errors
    public static final String WRONG_PASSWORD = "Wrong password";

    // addUser errors
    public static final String INVALID_ROLE = "Role must be client or manager";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String INVALID_USERNAME_CONTAINS_SPECIAL_CHARACTERS = "Username contains special characters";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";


    // addRestaurant errors
    public static final String RESTAURANT_ALREADY_EXISTS = "Restaurant already exists";

    // addTable errors
    public static final String TABLE_ID_NOT_UNIQUE = "Table id is not unique and already exists";
    public static final String RESTAURANT_DOES_NOT_EXIST = "Restaurant does not exist";
    public static final String INVALID_JSON_SEATS_NUMBER_NOT_NATURAL = "Seats number must be a natural number";

    // addReview errors
    public static final String INVALID_RATING = "Rating must be between 0 and 5";
    public static final String USER_IS_MANAGER = "managers cannot add reviews or reserve tables";

    // reserveTable errors
    public static final String DATETIME_FORMAT_INVALID = "Invalid date time format";
    public static final String TABLE_ID_IN_RESTAURANT_DOES_NOT_EXIST = "Table id in this restaurant does not exist";
    public static final String TABLE_IS_RESERVED = "Table is reserved";
    public static final String DATETIME_IS_PASSED = "Date time is passed";
    public static final String DATETIME_IS_NOT_IN_OPEN_HOURS = "Date time is not in open hours of the restaurant";

    // cancleReservation errors
    public static final String RESERVATION_DOES_NOT_EXIST = "Reservation does not exist";
    public static final String RESERVATION_TIME_PASSED = "Reservation time passed";
}
