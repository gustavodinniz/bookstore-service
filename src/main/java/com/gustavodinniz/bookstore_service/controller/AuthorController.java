package com.gustavodinniz.bookstore_service.controller;

import com.gustavodinniz.bookstore_service.model.dto.request.CreateAuthorRequest;
import com.gustavodinniz.bookstore_service.model.dto.request.UpdateAuthorRequest;
import com.gustavodinniz.bookstore_service.model.dto.response.GetAuthorByIdResponse;
import com.gustavodinniz.bookstore_service.service.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest, HttpServletResponse response) {
        var uuid = authorService.createAuthor(createAuthorRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        response.addHeader("location", location.toString());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetAuthorByIdResponse getAuthorById(@PathVariable String id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAuthorByIdResponse> getAuthorsByFilters(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "nationality", required = false) String nationality) {
        return authorService.getAuthorsByFilters(name, nationality);
    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable String id, @RequestBody UpdateAuthorRequest createAuthorRequest) {
        authorService.updateAuthor(id, createAuthorRequest);
    }
}
