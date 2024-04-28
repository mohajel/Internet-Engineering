package com.github.mohajel.IE.CA4.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Utils {

    static public String getCurrentTime() {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentTimeString = currentTime.format(formatter);
        return currentTimeString;
    }

    static public JSONObject pharseLine(String line) {

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

    // containing characters like @ # $ % ^ & * ( ) _ + - = { } [ ] | \ : " ; ' < >
    // , . ? / ~ ` and space
    static public boolean containsCharacters(String str) {
        return str.matches(".*[@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~` ].*");
    }

    static boolean isFirstLog = true;
    public static void logAppendFile(String functionName, String message) {
        // clear content of the file from previous runs
        if (isFirstLog) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("IE-CA4-logs.txt"));
                writer.write("");
                writer.close();
                isFirstLog = false;
            } catch (IOException ioe) {
                System.out.println("Couldn't write to file");
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("IE-CA4-logs.txt", true));
            writer.write("Function: " + functionName + " - " + message + "\n");
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't write to file");
        }
    }

}