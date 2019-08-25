package ru.geracimov.otus.spring.hw18webflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.services.AuthorService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/api/author")
    public Flux<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/api/author/search")
    public Flux<Author> getAllAuthorsByBirthBetween(@RequestParam("birthFrom")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                            LocalDate from,
                                                    @RequestParam("birthTo")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                            LocalDate to) {
        return authorService.getAllAuthorsByBirth(from, to);
    }

    @GetMapping("/api/author/{id}")
    public Mono<Author> showAuthorList(@PathVariable("id") String id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/api/author/{id}")
    public Mono<Void> deleteAuthor(@PathVariable("id") String id) {
        return authorService.deleteAuthorById(id);
    }

    @PostMapping("/api/author")
    public Mono<Author> addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/api/author/{id}")
    public Mono<Author> editAuthor(@PathVariable("id") String id, @RequestBody Author author) {
        author.setId(id);
        return authorService.save(author);
    }

}