package com.github.mohajel.IE.CA5;


import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Main.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));

		// get the instance of the app for Init it
		MizdooniApp.getInstance();
		
        app.run(args);
	}

}
