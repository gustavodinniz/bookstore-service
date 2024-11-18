package com.gustavodinniz.bookstore_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookstoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreServiceApplication.class, args);
	}

}
