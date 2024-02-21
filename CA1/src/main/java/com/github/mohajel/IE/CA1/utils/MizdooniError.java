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

    // addUser errors
    public static final String INVALID_ROLE = "Role must be client or manager";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String INVALID_USERNAME_CONTAINS_SPECIAL_CHARACTERS = "Username contains special characters";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";


}
