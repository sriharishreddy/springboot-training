package com.springboot.training.controller;

import com.springboot.training.domain.UserException;
import com.springboot.training.exception.InvalidUpdateDataException;
import com.springboot.training.exception.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;

@RestControllerAdvice
public class TrainingControllerAdvise{
    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UserException userException(InvalidUserException exception){
        String errorMessage = exception.getMessage();
        UserException usrexp = new UserException();
        usrexp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        usrexp.setError(errorMessage);

        return usrexp;
    }

    @ExceptionHandler(InvalidUpdateDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserException updateUserException(InvalidUpdateDataException exception){
        String errorMessage = exception.getMessage();
        UserException usrexp = new UserException();
        usrexp.setStatus(HttpStatus.NOT_FOUND.value());
        usrexp.setError(errorMessage);

        return usrexp;
    }


}
