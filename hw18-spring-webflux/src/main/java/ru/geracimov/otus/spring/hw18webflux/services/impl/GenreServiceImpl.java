package ru.geracimov.otus.spring.hw18webflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;
import ru.geracimov.otus.spring.hw18webflux.repository.GenreRepository;
import ru.geracimov.otus.spring.hw18webflux.services.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository gRepo;

    @Override
    public Mono<Genre> getGenreById(String id) {
        return gRepo.findById(id);
    }

    @Override
    public Flux<Genre> getAllGenres() {
        return gRepo.findAll();
    }

    @Override
    public Mono<Genre> saveGenre(Genre genre) {
        return gRepo.save(genre);
    }

    @Override
    public Mono<Void> deleteGenre(String id) {
        return gRepo.deleteById(id);
    }

}
