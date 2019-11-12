package ru.geracimov.otus.spring.hw20springsecurityauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.exception.NotFoundException;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.AuthorService;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.BookService;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.GenreService;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@NamedEntityGraph(name = "Book.authors.genres", attributeNodes = {@NamedAttributeNode("authors"), @NamedAttributeNode("genres")})
public class BookController {

    public final BookService bookService;
    public final GenreService genreService;
    public final AuthorService authorService;

    @GetMapping("/book")
    public String showAuthorList(@NotNull Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/book/add")
    public String showBookAddPage(Book book, Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book-add";
    }

    @PostMapping("/book/add")
    public String bookSave(@Valid Book book,
                           @NotNull BindingResult result) {
        if (result.hasErrors()) {
            return "book-add";
        }
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/edit")
    public String showAuthorEditPage(@PathVariable("id") UUID id,
                                     @NotNull Model model) {
        Book book = bookService.getBookById(id)
                               .orElseThrow(NotFoundException::new);
        model.addAttribute("allGenres", genreService.getAllGenres());
        model.addAttribute("allAuthors", authorService.getAllAuthors());
        model.addAttribute("book", book);
        return "book-edit";
    }

    @PostMapping("/book/{id}/edit")
    public String updateAuthor(@PathVariable("id") UUID id,
                               @Valid Book book,
                               @NotNull BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            return "book-edit";
        }
        bookService.save(book);
        return "redirect:/book";
    }

    @PostMapping("/book/{id}/delete")
    public String deleteUser(@PathVariable("id") UUID id) {
        Book book = bookService.getBookById(id)
                               .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        bookService.delete(book);
        return "redirect:/book";
    }

}