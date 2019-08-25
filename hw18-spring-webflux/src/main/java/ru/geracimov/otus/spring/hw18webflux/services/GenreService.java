package ru.geracimov.otus.spring.hw18webflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;

public interface GenreService {

    Mono<Genre> getGenreById(String id);

    Flux<Genre> getAllGenres();

    Mono<Genre> saveGenre(Genre genre);

    Mono<Void> deleteGenre(String id);

}