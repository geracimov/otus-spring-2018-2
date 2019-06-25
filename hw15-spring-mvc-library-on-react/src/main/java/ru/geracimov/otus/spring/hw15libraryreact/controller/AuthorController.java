package ru.geracimov.otus.spring.hw15libraryreact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;
import ru.geracimov.otus.spring.hw15libraryreact.services.AuthorService;
import ru.geracimov.otus.spring.hw15libraryreact.services.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9000", "http://localhost:8080"})
public class AuthorController {

    public final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/api/author")
    public List<Author> showAuthorList() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("/api/author/{id}")
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.delete(id);
    }

    @PostMapping("/api/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/api/author")
    public void editAuthor(@RequestBody Author author) {
        authorService.save(author);
    }

}