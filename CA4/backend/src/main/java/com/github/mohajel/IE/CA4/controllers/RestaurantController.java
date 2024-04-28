package com.github.mohajel.IE.CA4.controllers;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.mohajel.IE.CA4.models.Address;
import com.github.mohajel.IE.CA4.models.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    Logger logger = LoggerFactory.getLogger(RestaurantController.class);



    // not working don't know why
    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getImageWithMediaType(@PathVariable(value = "name") String imageName) throws IOException {
        logger.info("Request: " + "image");

        Resource resource = new ClassPathResource("static/restaurants/restaurant1.png");

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }

        // InputStream in = getClass()
        // .getResourceAsStream("./resources/static/restaurants/restaurant1.png");
        // return IOUtils.toByteArray(in);
    }

}