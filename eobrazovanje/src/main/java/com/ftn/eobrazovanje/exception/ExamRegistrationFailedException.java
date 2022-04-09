package com.ftn.eobrazovanje.exception;

public class ExamRegistrationFailedException extends BadRequestException{
    public ExamRegistrationFailedException(String message) {
        super(message);
    }
}
