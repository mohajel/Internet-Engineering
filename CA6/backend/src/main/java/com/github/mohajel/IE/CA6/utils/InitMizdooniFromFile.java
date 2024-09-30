package com.github.mohajel.IE.CA6.utils;

import org.json.JSONObject;

import com.github.mohajel.IE.CA6.MizdooniApp;

import java.io.InputStream;
import java.util.Scanner;

public class InitMizdooniFromFile {
    public static String input_file = "inputForInit.txt";
    
    

    public static void init(MizdooniApp app) {
        InputStream inputStream = InitMizdooniFromFile.class.getResourceAsStream(input_file);
        if (inputStream == null) {
            System.out.println("input file not found");
            return;
        }
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            JSONObject input = Utils.pharseLine(line);
            JSONObject output = executeCommand(input, app);
            System.out.println(output.toString());
        }
        scanner.close();
    }

    private static JSONObject executeCommand(JSONObject input, MizdooniApp app) {
        String command = input.getString("command");
        JSONObject data = input.getJSONObject("data");

        switch (command) {
            case "addUser":
                return app.addUser(data);
            case "addRestaurant":
                return app.addRestaurant(data);
            case "addTable":
                return app.addTable(data);
            case "reserveTable":
                return app.reserveTable(data);
            case "cancelReservation":
                return app.cancelReservation(data);
            case "showReservationHistory":
                return app.showReservationHistory(data);
            case "searchRestaurantsByName":
                return app.searchRestaurantsContainName(data);
            case "searchRestaurantsByType":
                return app.searchRestaurantsByType(data);
            case "searchRestaurantsByCity":
                return app.searchRestaurantsByCity(data);
            case "showAvailableTables":
                return app.showAvailableTables(data);
            case "addReview":
                return app.addReview(data);
            default:
                return new JSONObject().put("error", MizdooniError.INVALID_COMMAND);
        }
    }
}
