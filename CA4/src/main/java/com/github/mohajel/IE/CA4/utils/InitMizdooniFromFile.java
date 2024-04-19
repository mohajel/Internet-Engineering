package com.github.mohajel.IE.CA4.utils;

import org.json.JSONObject;

import com.github.mohajel.IE.CA4.MizdooniApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitMizdooniFromFile {
    public static String input_file = "src/main/java/com/github/mohajel/IE/CA2/utils/inputForInit.txt";
    public static void init(MizdooniApp app) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(input_file));
        } catch (FileNotFoundException e) {
            System.out.println("input file not found");
        }

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
