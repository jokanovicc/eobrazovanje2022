package com.ftn.eobrazovanje.exception;

public class UserNonExistentException extends NotFoundException{
    public UserNonExistentException(String message) {
        super(message);
    }
}
