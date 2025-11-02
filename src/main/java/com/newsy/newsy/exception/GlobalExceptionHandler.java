package com.newsy.newsy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientError(HttpClientErrorException e) {
        log.error("HTTP Client Error: {}", e.getMessage());
        return ResponseEntity.status(e.getStatusCode())
                .body(Map.of("error", "API request failed: " + e.getMessage()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleHttpServerError(HttpServerErrorException e) {
        log.error("HTTP Server Error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "External API is unavailable"));
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<?> handleResourceAccessException(ResourceAccessException e) {
        log.error("Resource Access Error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Unable to reach external API"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An unexpected error occurred"));
    }
}

