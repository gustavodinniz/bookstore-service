package com.gustavodinniz.bookstore_service.model.dto.request;

import com.gustavodinniz.bookstore_service.model.Author;

import java.time.LocalDate;

public record UpdateAuthorRequest(
        String name,
        LocalDate birthDate,
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
