package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.MediaType;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA4.MizdooniApp;
import com.github.mohajel.IE.CA4.models.Address;
import com.github.mohajel.IE.CA4.models.User;
import com.github.mohajel.IE.CA4.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StatusController {

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatus(HttpServletRequest r) {
        MizdooniApp app = MizdooniApp.getInstance();
        JSONObject output = new JSONObject();

        String user = (String) r.getAttribute("name");

        if (user == null) {
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
        
        if (result.getBoolean("success") == true) {
            String username = bodyJsonFormat.getString("username");
            String role = result.getJSONObject("data").getString("role");
            JwtUtils jwtUtils = JwtUtils.getInstance();
            String token = jwtUtils.generateAccessToken(username,  Arrays.asList(role));
            result.put("JWT", token);
        }

        logger.info("Login Response: \n" + result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String PostLogout(@RequestBody String body, HttpServletRequest r) {

        logger.info("Logout Request: " + body);

        String user = (String) r.getAttribute("name");
        logger.info("NAME:" + user);
        JSONObject result = new JSONObject();

        if (user == null) {
            result.put("success", false);
        } else {
            result.put("success", true);
        }
        logger.info("logout Response: " + result.toString());
        return result.toString();
    }

}