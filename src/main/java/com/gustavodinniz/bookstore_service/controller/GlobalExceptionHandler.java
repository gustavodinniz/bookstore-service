package com.gustavodinniz.bookstore_service.controller;

import com.gustavodinniz.bookstore_service.exception.AuthorNotFoundException;
import com.gustavodinniz.bookstore_service.exception.InvalidUuidFormatException;
import com.gustavodinniz.bookstore_service.model.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthorNotFoundException.class)
    public ErrorResponse handleNotFoundException(AuthorNotFoundException e) {
        return ErrorResponse.notFound(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUuidFormatException.class)
    public ErrorResponse handleInvalidUuidException(InvalidUuidFormatException e) {
        return ErrorResponse.defaultResponse(e.getMessage());
    }
}
