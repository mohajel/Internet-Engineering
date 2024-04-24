package com.github.mohajel.IE.CA4.controllers;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C {

    @GetMapping("/api")
    String index() {
        // return "Hello, World!";

        JSONObject output = new JSONObject();
        // output.put("success", true);
        output.put("title", "hii");
        return output.toString();
    }

}