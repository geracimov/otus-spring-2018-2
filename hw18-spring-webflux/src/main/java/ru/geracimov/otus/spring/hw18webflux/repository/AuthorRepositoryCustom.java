package ru.geracimov.otus.spring.hw18webflux.repository;

import reactor.core.publisher.Flux;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;


public interface AuthorRepositoryCustom {

    void removeAuthorsFromAllBooksByAuthorId(String id);

    Flux<Author> getAuthorsByBookId(String bookId);

}
