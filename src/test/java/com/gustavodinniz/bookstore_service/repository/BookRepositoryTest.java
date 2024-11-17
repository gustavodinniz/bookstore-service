package com.gustavodinniz.bookstore_service.repository;

import com.gustavodinniz.bookstore_service.model.Book;
import com.gustavodinniz.bookstore_service.model.BookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void createBook() {
         var book = new Book();
         book.setIsbn("9780132350884");
         book.setTitle("Clean Code: A Handbook of Agile Software Craftsmanship");
         book.setPublicationDate(LocalDate.of(2008, 8, 11));
         book.setGenre(BookGenre.TECHNOLOGY);
         book.setPrice(new BigDecimal("44.95"));
         book.setAuthor(authorRepository.findById(UUID.fromString("3784497d-ee75-4920-8abe-57c980b29e8f")).get());

         var bookSaved = bookRepository.save(book);

         System.out.println("Book created: " + bookSaved);

    }

}