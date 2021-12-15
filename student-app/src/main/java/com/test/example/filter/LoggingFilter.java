/*
 * (C) 2021 Dagangnet Technologies Sdn Bhd.
 */
package com.test.example.filter;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kumaresan Sinniah
 */

@Component
@Order(1)
public class LoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String url = httpReq.getRequestURI();
        Date start = new Date();

        logger.info("Request start: " + httpReq.getLocalAddr() + ": " + url);
        chain.doFilter(request, response);

        Date end = new Date();
        logger.info("Request end: " + url + ". Time taken: " + (end.getTime() - start.getTime()) + " ms");
    }

}
