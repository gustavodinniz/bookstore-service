package com.gustavodinniz.bookstore_service.model.dto.request;

import com.gustavodinniz.bookstore_service.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateAuthorRequest(
        @NotBlank(message = "400.001") @Size(max = 100, min = 2, message = "400.002")
        String name,

        @NotNull(message = "400.001") @Past(message = "400.003")
        LocalDate birthDate,

        @NotBlank(message = "400.001") @Size(max = 50, min = 2, message = "400.002")
        String nationality
) {

    public Author toAuthor() {
        Author author = new Author();
        author.setName(this.name());
        author.setBirthDate(this.birthDate());
        author.setNationality(this.nationality());
        return author;
    }
}
