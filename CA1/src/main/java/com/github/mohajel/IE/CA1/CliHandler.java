package com.github.mohajel.IE.CA1;

import org.json.JSONObject;

import com.github.mohajel.IE.CA1.utils.MizdooniError;
import com.github.mohajel.IE.CA1.utils.Utils;

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

        if (command.equals("addUser")) {
            return this.app.addUser(data);
        } else if (command.equals("addRestaurant")) {
            return this.app.addRestaurant(data);
        } else if (command.equals("addTable")) {
            return this.app.addTable(data);
        } else if (command.equals("reserveTable")) {
            return this.app.reserveTable(data);
        } else if (command.equals("cancelReservation")) {
            return this.app.cancelReservation(data);
        } else if (command.equals("showReservationHistory")) {
            return this.app.showReservationHistory(data);
        } else if (command.equals("searchRestaurantsByType")) {
            return this.app.searchRestaurantsByType(data);
        } else if (command.equals("showAvailableTables")) {
            return this.app.showAvailableTables(data);
        } else if (command.equals("addReview")) {
            return this.app.addReview(data);
        } else {
            return new JSONObject().put("error", MizdooniError.INVALID_COMMAND);
        }
    }
}
