package com.gustavodinniz.bookstore_service.model.dto.response;

import com.gustavodinniz.bookstore_service.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record GetAuthorByIdResponse(
        UUID id,
        String name,
        LocalDate birthDate,
        String nationality
) {

    public static GetAuthorByIdResponse valueOf(Author author) {
        return new GetAuthorByIdResponse(
                author.getId(),
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        );
    }
}
