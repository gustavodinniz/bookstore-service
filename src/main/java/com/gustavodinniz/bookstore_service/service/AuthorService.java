package com.gustavodinniz.bookstore_service.service;

import com.gustavodinniz.bookstore_service.exception.AuthorNotFoundException;
import com.gustavodinniz.bookstore_service.exception.InvalidUuidFormatException;
import com.gustavodinniz.bookstore_service.model.Author;
import com.gustavodinniz.bookstore_service.model.dto.request.CreateAuthorRequest;
import com.gustavodinniz.bookstore_service.model.dto.request.UpdateAuthorRequest;
import com.gustavodinniz.bookstore_service.model.dto.response.GetAuthorByIdResponse;
import com.gustavodinniz.bookstore_service.repository.AuthorRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

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
                    return new AuthorNotFoundException("404.001");
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
            throw new InvalidUuidFormatException("400.004");
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

    public void updateAuthor(String id, UpdateAuthorRequest createAuthorRequest) {
        log.info("Starting operation to update author by ID: {}", id);
        var authorId = validadeUUID(id);
        authorRepository.findById(authorId)
                .ifPresentOrElse(author -> {
                    log.info("Author found for ID: {}", id);
                    author.setName(Optional.ofNullable(createAuthorRequest.name()).orElse(author.getName()));
                    author.setBirthDate(Optional.ofNullable(createAuthorRequest.birthDate()).orElse(author.getBirthDate()));
                    author.setNationality(Optional.ofNullable(createAuthorRequest.nationality()).orElse(author.getNationality()));
                    authorRepository.save(author);
                    log.info("Author updated for ID: {}", id);
                }, () -> {
                    log.warn("No author found for ID: {}", id);
                    throw new AuthorNotFoundException("404.001");
                });
    }
}
