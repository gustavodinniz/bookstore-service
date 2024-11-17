package com.gustavodinniz.bookstore_service.repository;

import com.gustavodinniz.bookstore_service.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void createAuthor() {
        var author = new Author();
        author.setName("Robert C. Martin");
        author.setNationality("American");
        author.setBirthDate(LocalDate.of(1952, 12, 5));

        var authorSaved = authorRepository.save(author);

        System.out.println("Author created: " + authorSaved);
    }

    @Test
    void updateAuthor() {
        var uuid = UUID.fromString("3784497d-ee75-4920-8abe-57c980b29e8f");
        var author = authorRepository.findById(uuid).get();
        author.setNationality("Brazilian");

        var authorUpdated = authorRepository.save(author);

        System.out.println("Author updated: " + authorUpdated);
    }

    @Test
    void listAuthors() {
        var authors = authorRepository.findAll();

        authors.forEach(System.out::println);
    }

    @Test
    void countAuthors() {
        var count = authorRepository.count();

        System.out.println("There are " + count + " authors in the database.");
    }

    @Test
    void deleteAuthorById() {
        var uuid = UUID.fromString("3784497d-ee75-4920-8abe-57c980b29e8f");
        authorRepository.deleteById(uuid);
    }

}