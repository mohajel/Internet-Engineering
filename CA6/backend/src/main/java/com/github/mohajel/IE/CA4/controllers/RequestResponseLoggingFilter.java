package com.github.mohajel.IE.CA4.controllers;

import java.rmi.ServerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServerException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            logger.info(
                    ">>>> Logging Request  {} : {}", req.getMethod(),
                    req.getRequestURI());
            chain.doFilter(request, response);
            logger.info(
                    ">>>>> Logging Response :{}",
                    res.getContentType());

        } catch (Exception e) {
            logger.info("sth bad happend", e);
        }

    }

    // other methods
}