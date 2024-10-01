package com.github.mohajel.IE.CA7.utils;

import org.json.JSONObject;

import com.github.mohajel.IE.CA7.MizdooniApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitMizdooniFromFile {
    public static String input_file = "src/main/java/com/github/mohajel/IE/CA7/utils/inputForInit.txt";
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

        return switch (command) {
            case "addUser" -> app.addUser(data);
            case "addRestaurant" -> app.addRestaurant(data);
            case "addTable" -> app.addTable(data);
            case "reserveTable" -> app.reserveTable(data);
            case "cancelReservation" -> app.cancelReservation(data);
            case "showReservationHistory" -> app.showReservationHistory(data);
            case "searchRestaurantsByName" -> app.searchRestaurantsContainName(data);
            case "searchRestaurantsByType" -> app.searchRestaurantsByType(data);
            case "searchRestaurantsByCity" -> app.searchRestaurantsByCity(data);
//            case "showAvailableTables" -> app.showAvailableTables(data);
            case "addReview" -> app.addReview(data);
            default -> new JSONObject().put("error", MizdooniError.INVALID_COMMAND);
        };
    }
}
