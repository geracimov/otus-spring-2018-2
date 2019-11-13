package ru.geracimov.otus.spring.hw18webflux.repository;

import reactor.core.publisher.Mono;

public interface BookRepositoryCustom {

    Mono<Void> removeAuthorFromBookById(String id);

}
