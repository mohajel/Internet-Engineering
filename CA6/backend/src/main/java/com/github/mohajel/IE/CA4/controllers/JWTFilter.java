package com.github.mohajel.IE.CA4.controllers;

import java.rmi.ServerException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.github.mohajel.IE.CA4.utils.JwtUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class JWTFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(StatusController.class);
    JwtUtils jwtUtils = JwtUtils.getInstance();

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServerException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            logger.info(
                    // ">>>> Logging Request {} : {}", req.getMethod(),
                    req.getRequestURI());

            Cookie JWTCookie = WebUtils.getCookie(req, "JWT");

            if (JWTCookie != null) { // 
                    String token = JWTCookie.getValue();

                    if (jwtUtils.validateJwtToken(token)) { //is valid token
                        String username = jwtUtils.getSubject(token);
                        req.setAttribute("name", username);

                    } else { // is not valid token
                        // return error or sth 
                        logger.info(" ------------------- JWT NOT VALID TOKEN !!!!");
                    }

                    // logger.info("JWT COOKIEEEE FOUND " + token);
                    logger.info("JWT COOKIEEEE FOUND");
            } else {
                logger.info("NO COOKIEEEE!");
            }
            // String path = "PATH " + req.getRequestURL() + " >";
            // String path = "PATH " + req.getRequestURI().substring(req.getContextPath().length());
            String path = req.getRequestURI().substring(req.getContextPath().length());
            logger.info(path);

            chain.doFilter(request, response);
            logger.info(
                    // ">>>>> Logging Response :{}",
                    res.getContentType());

        } catch (Exception e) {
            logger.info("Exception in JWT Filter ", e);
        }

    }
}