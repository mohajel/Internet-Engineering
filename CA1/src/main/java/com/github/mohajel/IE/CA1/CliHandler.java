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
            String line  = this.scanner.nextLine();
            JSONObject input = this.utils.pharseLine(line);

            
        }

        scanner.close();
    }
}