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

    Logger logger = LoggerFactory.getLogger(JWTFilter.class);
    JwtUtils jwtUtils = JwtUtils.getInstance();

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServerException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            
            Cookie JWTCookie = WebUtils.getCookie(req, "JWT");

            String path = req.getRequestURI().substring(req.getContextPath().length());
            logger.info("Path: " + path);

            if (JWTCookie != null) { //
                logger.info("JWT Token Found");
                String token = JWTCookie.getValue();

                if (jwtUtils.validateJwtToken(token)) { // is valid token
                    logger.info("JWT Token Is Valid");

                    String username = jwtUtils.getSubject(token);
                    req.setAttribute("name", username);

                    if (path.startsWith("/login") || path.startsWith("/users/signup")) {
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                        return;
                    }

                } else { // is not valid token
                    logger.warn(" JWT Not Valid Token !!!!");
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                    return;
                }
            } else {
                logger.info("JWT Token Not Found");

                if (!(path.startsWith("/login") || path.startsWith("/users/signup") || path.startsWith("/status"))) {
                    logger.warn(" Accessing UnAuthurized Part ");
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Accessing UnAuthurized Part ");
                    return;
                }
            }
            chain.doFilter(request, response);

        } catch (Exception e) {
            logger.warn("Exception in JWT Filter ", e);
        }
    }
}