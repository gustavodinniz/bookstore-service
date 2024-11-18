package com.gustavodinniz.bookstore_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gustavodinniz.bookstore_service.model.dto.FieldWithError;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(int status, String message, List<FieldWithError> errors) {

    public static ErrorResponse defaultResponse(String message){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }

    public static ErrorResponse notFound(String message){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), message, null);
    }

}
