package com.oasis.taskmanagementapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasis.taskmanagementapp.dto.ApiError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public RestAccessDeniedHandler(@Qualifier("oasisObjectMapper") ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ApiError error = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                "You are not authorized",
                request.getRequestURI()
        );

        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, error);
        out.flush();
    }
}
