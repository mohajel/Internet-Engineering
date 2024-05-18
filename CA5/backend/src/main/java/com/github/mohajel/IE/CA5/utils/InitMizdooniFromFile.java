package com.github.mohajel.IE.CA5.utils;

import org.json.JSONObject;

import com.github.mohajel.IE.CA5.MizdooniApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.github.mohajel.IE.CA5.utils.Utils.logAppendFile;

public class InitMizdooniFromFile {
    public static String input_file = "/backend/src/main/java/com/github/mohajel/IE/CA5/utils/inputForInit.txt";
    
    

    public static void init(MizdooniApp app) throws FileNotFoundException {
        String currentDir = System.getProperty("user.dir") + input_file;
        File file = new File(currentDir);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            JSONObject input = Utils.pharseLine(line);
            JSONObject output = executeCommand(input, app);
            logAppendFile("initReserve", output.toString());
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
//            case "searchRestaurantsByType" -> app.searchRestaurantsByType(data);
//            case "searchRestaurantsByCity" -> app.searchRestaurantsByCity(data);
//            case "showAvailableTables" -> app.showAvailableTables(data);
            case "addReview" -> app.addReview(data);
            default -> new JSONObject().put("error", MizdooniError.INVALID_COMMAND);
        };
    }
}
