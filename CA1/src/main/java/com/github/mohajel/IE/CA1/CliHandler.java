package com.github.mohajel.IE.CA1;

import org.json.JSONObject;
import java.util.Scanner;

public class CliHandler {

    private MizdooniApp app;
    private Scanner scanner;
    private Utils utils;

    public CliHandler(MizdooniApp app) {
        this.app = app;
        scanner = new Scanner(System.in);
        utils = new Utils();
    }

    public void start() {

        while (scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            JSONObject input = this.utils.pharseLine(line);
            JSONObject output = this.executeCommand(input);
            System.out.println(output.toString());
        }

        scanner.close();
    }

    private JSONObject executeCommand(JSONObject input) {
        String command = input.getString("command");
        JSONObject data = input.getJSONObject("data");

        if (command == "addUser") {
            return this.app.addUser(data);
        } else if (command == "addRestaurant") {
            return this.app.addRestaurant(data);
        } else if (command == "addTable") {
            return this.app.addTable(data);
        } else if (command == "reserveTable") {
            return this.app.reserveTable(data);
        } else if (command == "cancelReservation") {
            return this.app.cancelReservation(data);
        } else if (command == "showReservationHistory") {
            return this.app.showReservationHistory(data);
        } else if (command == "searchRestaurantsByType") {
            return this.app.searchRestaurantsByType(data);
        } else if (command == "showAvailableTables") {
            return this.app.showAvailableTables(data);
        } else if (command == "addReview") {
            return this.app.addReview(data);
        } else {
            return new JSONObject().put("error", "command not found");
        }

    }

}