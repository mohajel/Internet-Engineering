package com.github.mohajel.IE.CA4.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.github.mohajel.IE.CA4.MizdooniApp;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    final String github_client_id = "92b571684150ec1038aa";
    final String github_client_secret = "b7e5883f63b3e9aab294a8772222ad7e0a787b83";

    @GetMapping("/github/{code}")
    public String getGithubAuthRedirect(@PathVariable String code) {

        MizdooniApp app = MizdooniApp.getInstance();

        String accessToken = getAccessToken(code);

        logger.warn("ACCESS_TOKEN :" + accessToken + ">>");

        JSONObject userInfo = getGithubUserInfo(accessToken, code);

        logger.warn("USER :" + userInfo.toString() + ">>");

        JSONObject result = app.addUser(userInfo);
        return result.toString();
    }

    private JSONObject getGithubUserInfo(String access_token, String code) {

        JSONObject output = new JSONObject();
        
        try {
            String url = "https://api.github.com/user";
            String auth = "Bearer " + access_token;
            
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("Authorization", auth);
            
            HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
             ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            JSONObject body = new JSONObject(response.getBody());
            // return body.getString("access_token");
    
            // select what we need
            String username = body.getString("login");
    
            output.put("username", username);
            output.put("password", username + "-1234");
            output.put("email", username + "@github.com");
            output.put("role", "client");
            return output;
        } catch (Exception e) {
            logger.warn("GET USER GITHUB EXCEPTION ECCURD");
        }
        return output;
    }

    private String getAccessToken(String code) {
        
        try {
            String url = "https://github.com/login/oauth/access_token";
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap<>();
            params.put("client_id", github_client_id);
            params.put("client_secret", github_client_secret);
            params.put("code", code);
    
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
    
            ResponseEntity<String> response = null;
            HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
            response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);            
            JSONObject body = new JSONObject(response.getBody());
            return body.getString("access_token");
        } catch (Exception e) {
            logger.warn("GET ACEESS TOKEN GITHUB EXCEPTIONN ACCURED");
        }
        return "";
    }
}
