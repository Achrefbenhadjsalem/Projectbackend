package com.test.COCONSULT.Errors;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg){
        super(msg);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
