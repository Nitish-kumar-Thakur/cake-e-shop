package com.onlinebackery.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler{
    @ExceptionHandler(Exception.class)
    public String error(){
        return "403";
    }
}
