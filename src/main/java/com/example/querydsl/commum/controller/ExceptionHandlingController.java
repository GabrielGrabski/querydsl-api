package com.example.querydsl.commum.controller;

import com.example.querydsl.commum.MessageException;
import com.example.querydsl.commum.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public List<MessageException> validationError(@Validated ValidationException ex) {
        return List.of(new MessageException(ex.getMessage()));
    }
}
