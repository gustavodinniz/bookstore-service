package com.gustavodinniz.bookstore_service.service;

import com.gustavodinniz.bookstore_service.model.Author;
import com.gustavodinniz.bookstore_service.model.dto.request.CreateAuthorRequest;
import com.gustavodinniz.bookstore_service.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author createAuthor(CreateAuthorRequest createAuthorRequest) {
        log.info("Creating author: {}", createAuthorRequest.name());
        var author = createAuthorRequest.toAuthor();
        authorRepository.save(author);
        log.info("Author created with ID: {}", author.getId());
        return author;
    }
}
