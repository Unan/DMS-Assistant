package com.griddynamics.dms.controller;

import com.griddynamics.dms.config.security.user.User;
import com.griddynamics.dms.exception.EmployeeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SurveyErrorController {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @User
    public ResponseEntity<String> notFoundException(final EmployeeNotFoundException e) {
        return ResponseEntity.status(404).body(" Not found");
    }
}