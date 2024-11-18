package com.gustavodinniz.bookstore_service.controller;

import com.gustavodinniz.bookstore_service.model.dto.request.CreateAuthorRequest;
import com.gustavodinniz.bookstore_service.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
        var author = authorService.createAuthor(createAuthorRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
