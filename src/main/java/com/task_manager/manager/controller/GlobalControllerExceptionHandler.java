package com.task_manager.manager.controller;

import com.task_manager.manager.exception.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(ExpectedException.class)
    public ResponseEntity error(ExpectedException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity error(ValidationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }


}
