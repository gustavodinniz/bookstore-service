package com.gustavodinniz.bookstore_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gustavodinniz.bookstore_service.model.dto.FieldErrorDTO;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(int status, String message, List<FieldErrorDTO> errors) {

    public static ErrorResponse defaultResponse(String message){
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .errors(null)
                .build();
    }

    public static ErrorResponse conflict(String message){
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(message)
                .errors(null)
                .build();
    }

    public static ErrorResponse notFound(String message){
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(message)
                .errors(null)
                .build();
    }

}
