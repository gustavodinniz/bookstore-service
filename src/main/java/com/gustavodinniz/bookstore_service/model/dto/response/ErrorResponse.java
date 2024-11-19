package com.gustavodinniz.bookstore_service.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gustavodinniz.bookstore_service.model.dto.FieldErrorDTO;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(String code, String message, List<FieldErrorDTO> errors) {

    public static ErrorResponse defaultResponse(String code, String message){
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse conflict(String message){
        return ErrorResponse.builder()
                .message(message)
                .build();
    }

    public static ErrorResponse notFound(String message){
        return ErrorResponse.builder()
                .message(message)
                .build();
    }

}
