package ru.geracimov.otus.spring.hw18webflux.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;


public interface AuthorRepositoryCustom {

    Mono<Void> removeAuthorsFromAllBooksByAuthorId(String id);

    Flux<Author> getAuthorsByBookId(String bookId);

}
