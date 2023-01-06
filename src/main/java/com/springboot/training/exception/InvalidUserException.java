package com.springboot.training.exception;

public class InvalidUserException extends Exception{
    public InvalidUserException(){

    }
    public InvalidUserException(String errorMessage){
        super(errorMessage);
    }
}
