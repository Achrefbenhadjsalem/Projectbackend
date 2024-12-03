package com.test.COCONSULT.JWT;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
            throws IOException, ServletException {

        logger.error("Unauthorized error. Message - {}", e.getMessage());

        // Check if the exception message indicates an expired token
        if (e.getCause() instanceof ExpiredJwtException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Token expired");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
        }
    }
}
