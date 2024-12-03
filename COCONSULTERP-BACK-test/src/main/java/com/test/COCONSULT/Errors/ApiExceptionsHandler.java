package com.test.COCONSULT.Errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionsHandler {

@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions (NotFoundException ex, WebRequest request){
        ErrorDetails details = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(details, ex.getStatusCode());

}}
