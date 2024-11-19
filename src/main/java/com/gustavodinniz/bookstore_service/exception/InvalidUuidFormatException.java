package com.gustavodinniz.bookstore_service.exception;

public class InvalidUuidFormatException extends RuntimeException {


    public InvalidUuidFormatException(String message) {
        super(message);
    }


    public InvalidUuidFormatException(String message, Throwable cause) {
        super(message, cause);
    }


    public InvalidUuidFormatException(Throwable cause) {
        super(cause);
    }
}
