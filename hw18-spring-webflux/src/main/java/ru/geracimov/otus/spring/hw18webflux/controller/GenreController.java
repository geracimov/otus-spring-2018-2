package ru.geracimov.otus.spring.hw18webflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;
import ru.geracimov.otus.spring.hw18webflux.services.GenreService;


@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/api/genre")
    public Flux<Genre> showGenreList() {
        return genreService.getAllGenres();
    }

    @GetMapping("/api/genre/{id}")
    public Mono<Genre> getGenreById(@PathVariable String id) {
        return genreService.getGenreById(id);
    }

    @DeleteMapping("/api/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable String id) {
        return genreService.deleteGenre(id);
    }

    @PostMapping("/api/genre")
    public Mono<Genre> genreAdd(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @PutMapping("/api/genre/{id}")
    public Mono<Genre> genreEdit(@RequestBody Genre genre,
                                 @PathVariable String id) {
        genre.setId(id);
        return genreService.saveGenre(genre);
    }

}