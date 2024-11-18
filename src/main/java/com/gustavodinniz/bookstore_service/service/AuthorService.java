package com.gustavodinniz.bookstore_service.service;

import com.gustavodinniz.bookstore_service.exception.AuthorNotFoundException;
import com.gustavodinniz.bookstore_service.model.Author;
import com.gustavodinniz.bookstore_service.model.dto.request.CreateAuthorRequest;
import com.gustavodinniz.bookstore_service.model.dto.response.GetAuthorByIdResponse;
import com.gustavodinniz.bookstore_service.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public UUID createAuthor(CreateAuthorRequest createAuthorRequest) {
        log.info("Starting operation to create author: {}", createAuthorRequest.name());
        var author = createAuthorRequest.toAuthor();
        authorRepository.save(author);
        log.info("Author created with ID: {}", author.getId());
        return author.getId();
    }

    public GetAuthorByIdResponse getAuthorById(@NonNull String id) {
        log.info("Starting operation to get author by ID: {}", id);
        var authorId = validadeUUID(id);
        return authorRepository.findById(authorId)
                .map(author -> {
                    log.info("Author found for ID: {}", id);
                    return GetAuthorByIdResponse.valueOf(author);
                })
                .orElseThrow(() -> {
                    log.warn("No author found for ID: {}", id);
                    return new AuthorNotFoundException("Author not found for ID: " + id);
                });
    }

    public void deleteAuthor(String id) {
        log.info("Starting operation to delete author by ID: {}", id);
        var authorId = validadeUUID(id);
        authorRepository.deleteById(authorId);
        log.info("Author deleted for ID: {}", id);
    }

    private UUID validadeUUID(String id) {
        UUID authorId;
        try {
            authorId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format for ID: {}", id, e);
            throw new IllegalArgumentException("Invalid ID format. Expected UUID.");
        }
        return authorId;
    }


    public List<GetAuthorByIdResponse> getAuthorsByFilters(String name, String nationality) {
        log.info("Starting operation to get authors by filters: {}, {}", name, nationality);
        var author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        var matcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "birthDate")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var authorExample = Example.of(author, matcher);
        var authors = authorRepository.findAll(authorExample)
                .stream()
                .map(GetAuthorByIdResponse::valueOf)
                .toList();
        log.info("Authors found by filters. Size: {}", authors.size());
        return authors;
    }
}
