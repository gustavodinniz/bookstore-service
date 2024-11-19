package com.gustavodinniz.bookstore_service.model.dto.request;

import com.gustavodinniz.bookstore_service.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAuthorRequest(
        @NotBlank String name,
        @NotNull LocalDate birthDate,
        @NotBlank String nationality
) {

    public Author toAuthor() {
        Author author = new Author();
        author.setName(this.name());
        author.setBirthDate(this.birthDate());
        author.setNationality(this.nationality());
        return author;
    }
}
