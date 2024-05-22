package com.github.mohajel.IE.CA4.controllers;

import java.util.Collections;
// import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import org.apache.commons.codec.binary.Base64;

import com.github.mohajel.IE.CA4.MizdooniApp;
import com.github.mohajel.IE.CA4.utils.JwtUtils;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// try {
		// 	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		// 	kpg.initialize(2048);
		// 	KeyPair kp = kpg.generateKeyPair();

		// 	String publicKey = Base64.encodeBase64String(kp.getPublic().getEncoded());
		// 	String privateKey = Base64.encodeBase64String(kp.getPrivate().getEncoded());
		// 	JwtUtils jwtUtils = new JwtUtils();

		// 	String jwtToken = jwtUtils.generateAccessToken("Ajinkya", Arrays.asList("ADMIN"), privateKey);

		// 	System.out.println(jwtUtils.validateJwtToken(jwtToken, publicKey));

		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));

		// get the instance of the app for Init it
		MizdooniApp.getInstance();

		app.run(args);
	}

}
