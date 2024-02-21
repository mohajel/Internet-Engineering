package com.github.mohajel.IE.CA1.utils;

import org.json.JSONObject;

public class Utils {

    public JSONObject pharseLine(String line) {

        int separatorIndex = Math.min(line.indexOf("{"), line.indexOf(" "));
        String command = line.substring(0, separatorIndex).trim();

        String dataStringForm = line.substring(separatorIndex);

        JSONObject input = new JSONObject();
        input.put("command", command);
        input.put("data", new JSONObject(dataStringForm));

        return input;
    }

    static public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static public boolean isEmail(String str) {
        return str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    static public boolean isPhoneNumber(String str) {
        return str.matches("\\d{11}");
    }

    static public boolean isTime(String str) {
        return str.matches("\\d{2}:\\d{2}");
    }

    // containing characters like @ # $ % ^ & * ( ) _ + - = { } [ ] | \ : " ; ' < > , . ? / ~ ` and space
    static public boolean containsCharacters(String str) {
        return str.matches(".*[@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~` ].*");
    }

}