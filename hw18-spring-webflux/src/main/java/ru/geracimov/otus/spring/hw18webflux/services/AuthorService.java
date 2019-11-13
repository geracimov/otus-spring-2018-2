package ru.geracimov.otus.spring.hw18webflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;

import java.time.LocalDate;

public interface AuthorService {

    Flux<Author> getAllAuthors();

    Flux<Author> getAllAuthorsByBirth(LocalDate from, LocalDate to);

    Mono<Author> getAuthorById(String id);

    Mono<Void> deleteAuthorById(String id);

    Mono<Author> save(Author author);

}
