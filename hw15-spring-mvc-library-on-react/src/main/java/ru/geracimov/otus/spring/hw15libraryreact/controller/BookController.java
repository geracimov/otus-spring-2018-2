package ru.geracimov.otus.spring.hw15libraryreact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Book;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Genre;
import ru.geracimov.otus.spring.hw15libraryreact.services.AuthorService;
import ru.geracimov.otus.spring.hw15libraryreact.services.BookService;
import ru.geracimov.otus.spring.hw15libraryreact.services.GenreService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9000", "http://localhost:8080"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/api/book")
    public Iterable<Book> showBookList() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/book/{id}/author")
    public List<Author> getAllAuthorsByBook(@PathVariable UUID id) {
        return authorService.getAllAuthorsByBook(id);
    }

    @GetMapping("/api/book/{id}/genre")
    public Iterable<Genre> showBookList(@PathVariable UUID id) {
        return genreService.getGenresByBook(id);
    }

    @DeleteMapping("/api/book/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.delete(id);
    }

    @PostMapping("/api/book")
    public Book bookAdd(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/api/book")
    public void bookEdit(@RequestBody Book book) {
        System.out.println(book);
        bookService.save(book);
    }

}