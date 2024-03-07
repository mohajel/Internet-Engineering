package com.github.mohajel.IE.CA2;

import org.json.JSONObject;

import com.github.mohajel.IE.CA2.utils.MizdooniError;
import com.github.mohajel.IE.CA2.utils.Utils;

import java.util.Scanner;

public class CliHandler {

    private MizdooniApp app;
    private Scanner scanner;

    public CliHandler(MizdooniApp app) {
        this.app = app;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            JSONObject input = Utils.pharseLine(line);
            JSONObject output = this.executeCommand(input);
            System.out.println(output.toString());
        }
        scanner.close();
    }

    private JSONObject executeCommand(JSONObject input) {
        String command = input.getString("command");
        JSONObject data = input.getJSONObject("data");

        switch (command) {
            case "addUser":
                return this.app.addUser(data);
            case "addRestaurant":
                return this.app.addRestaurant(data);
            case "addTable":
                return this.app.addTable(data);
            case "reserveTable":
                return this.app.reserveTable(data);
            case "cancelReservation":
                return this.app.cancelReservation(data);
            case "showReservationHistory":
                return this.app.showReservationHistory(data);
            case "searchRestaurantsByName":
                return this.app.searchRestaurantsContainName(data);
            case "searchRestaurantsByType":
                return this.app.searchRestaurantsByType(data);
            case "searchRestaurantsByCity":
                return this.app.searchRestaurantsByCity(data);
            case "showAvailableTables":
                return this.app.showAvailableTables(data);
            case "addReview":
                return this.app.addReview(data);
            default:
                return new JSONObject().put("error", MizdooniError.INVALID_COMMAND);
        }
    }
}
