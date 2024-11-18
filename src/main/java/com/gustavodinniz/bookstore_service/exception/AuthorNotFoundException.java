package com.gustavodinniz.bookstore_service.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException() {
        super("Author not found");
    }


    public AuthorNotFoundException(String message) {
        super(message);
    }


    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public AuthorNotFoundException(Throwable cause) {
        super(cause);
    }
}
