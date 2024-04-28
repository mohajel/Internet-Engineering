package com.github.mohajel.IE.CA4.controllers;


import java.util.Collections;

import com.github.mohajel.IE.CA4.MizdooniApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));

		// get the instance of the app for Init it
		MizdooniApp.getInstance();
        app.run(args);
	}

}
