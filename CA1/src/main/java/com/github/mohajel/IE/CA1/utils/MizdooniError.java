package com.github.mohajel.IE.CA1.utils;

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
    public static final String UNKOOWN_ERROR = "Unknown error occurred";

    // addUser errors
    public static final String INVALID_ROLE = "Role must be client or manager";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String INVALID_USERNAME_CONTAINS_SPECIAL_CHARACTERS = "Username contains special characters";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";

    public static final String HOUR_IS_NOT_ROUND = "Hour is not round";

    public static final String RESTAURANT_ALREADY_EXISTS = "Restaurant already exists";

    public static final String USER_DOES_NOT_EXIST = "User does not exist";

    public static final String USER_IS_NOT_MANAGER = "User is not a manager";
}
