package com.gustavodinniz.bookstore_service.repository;

import com.gustavodinniz.bookstore_service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAuthorsByNameAndNationality(String name, String nationality);
}
