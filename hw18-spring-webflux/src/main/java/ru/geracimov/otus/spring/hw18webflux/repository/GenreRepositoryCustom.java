package ru.geracimov.otus.spring.hw18webflux.repository;

import reactor.core.publisher.Mono;

public interface GenreRepositoryCustom {

    Mono<Void> removeGenreFromAllBooksByGenreId(String id);

}
