package com.ftn.eobrazovanje.exception;

public class StudentNonExistentException extends NotFoundException{
    public StudentNonExistentException(String message) {
        super(message);
    }
}
