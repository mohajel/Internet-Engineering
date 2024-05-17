package com.github.mohajel.IE.CA5.utils;

import com.github.mohajel.IE.CA5.MizdooniApp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.github.mohajel.IE.CA5.utils.Utils.logAppendFile;

public class InitMizdooniFromAPI {
    private static final String URL_API = "http://91.107.137.117:55";

    public static void init(MizdooniApp app) {
        // TODO: uncomment the following lines
        initUsers(app);
        initRestaurants(app);
//        initTables(app);
//        initReviews(app);
    }

    private static JSONArray getDataFromAPI(String url) {
        JSONArray jsonArray = null;
        try (InputStream inputStream = new URL(url).openStream()) {
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            jsonArray = new JSONArray(new JSONTokener(json));
        } catch (Exception e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }

        return jsonArray;
    }

    private static void initRestaurants(MizdooniApp app) {
        JSONArray restaurants = getDataFromAPI(URL_API + "/restaurants");
        for (int i = 0; i < restaurants.length(); i++) {
            JSONObject data = restaurants.getJSONObject(i);
            JSONObject result = app.addRestaurant(data);
            logAppendFile("initRestaurants", result.toString());
        }
    }

    private static void initUsers(MizdooniApp app) {
        JSONArray users = getDataFromAPI(URL_API + "/users");
        for (int i = 0; i < users.length(); i++) {
            JSONObject data = users.getJSONObject(i);
            JSONObject result = app.addUser(data);
            logAppendFile("initUsers", result.toString());
        }
    }

    private static void initTables(MizdooniApp app) {
        JSONArray tables = getDataFromAPI(URL_API + "/tables");
        for (int i = 0; i < tables.length(); i++) {
            JSONObject data = tables.getJSONObject(i);
            JSONObject result = app.addTable(data);
            logAppendFile("initTables", result.toString());
        }
    }

    private static void initReviews(MizdooniApp app) {
        JSONArray reviews = getDataFromAPI(URL_API + "/reviews");
        for (int i = 0; i < reviews.length(); i++) {
            JSONObject data = reviews.getJSONObject(i);
            JSONObject result = app.addReview(data);
            logAppendFile("initReviews", result.toString());
        }
    }
}
