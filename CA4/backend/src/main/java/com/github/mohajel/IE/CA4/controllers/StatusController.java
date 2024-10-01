package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.MediaType;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA4.MizdooniApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class StatusController {

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatus() {
        MizdooniApp app = MizdooniApp.getInstance();
        String user = app.loggedInUser;
        JSONObject output = new JSONObject();

        if (user.length() == 0) {
            output.put("status", "loggedOut");
        } else {
            output.put("status", "loggedIn");
            output.put("role", app.db.getUserByUserName(user).role.toString());
            output.put("username", user);
        }

        logger.info("Status: " + output.toString());
        return output.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String PostLogin(@RequestBody String body) {

        MizdooniApp app = MizdooniApp.getInstance();
        logger.info("Login Request: \n" + body);
        JSONObject bodyJsonFormat = new JSONObject(body);

        JSONObject result = app.login(bodyJsonFormat);

        logger.info("Login Response: \n" + result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String PostLogout(@RequestBody String body) {

        logger.info("Logout Request: " + body);

        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject result = new JSONObject();

        if (app.loggedInUser.length() == 0) {
            result.put("success", false);
        } else {
            app.loggedInUser = "";
            result.put("success", true);
        }
        logger.info("logout Response: " + result.toString());
        return result.toString();
    }

}