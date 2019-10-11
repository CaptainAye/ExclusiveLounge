package com.exclusivelounge.rental.controller.controllers;

import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandNotFoundException;
import com.exclusivelounge.rental.domains.assets.model.exceptions.BrandTypeIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Object> handleBrandNotFoundException() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(BrandTypeIncorrectException.class)
    public ResponseEntity<Object> handleBrandTypeIncorrectException() {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Model set for the asset is not of a proper type");
    }
}
