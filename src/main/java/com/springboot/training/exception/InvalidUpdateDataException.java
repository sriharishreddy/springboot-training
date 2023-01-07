package com.springboot.training.exception;

public class InvalidUpdateDataException extends Exception{
    public InvalidUpdateDataException(){

    }

    public InvalidUpdateDataException(String errorMessage){
        super(errorMessage);
    }
}
