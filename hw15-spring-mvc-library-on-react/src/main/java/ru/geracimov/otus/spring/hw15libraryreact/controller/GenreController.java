package ru.geracimov.otus.spring.hw15libraryreact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Genre;
import ru.geracimov.otus.spring.hw15libraryreact.services.GenreService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9000", "http://localhost:8080"})
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/api/genre")
    public List<Genre> showGenreList() {
        return genreService.getAllGenres();
    }

    @DeleteMapping("/api/genre/{id}")
    public void deleteGenre(@PathVariable UUID id) {
        genreService.delete(id);
    }

    @PostMapping("/api/genre")
    public Genre genreAdd(@RequestBody Genre genre) {
        return genreService.addGenre(genre.getName());
    }

    @PutMapping("/api/genre")
    public void genreEdit(@RequestBody Genre genre) {
        genreService.save(genre);
    }

}