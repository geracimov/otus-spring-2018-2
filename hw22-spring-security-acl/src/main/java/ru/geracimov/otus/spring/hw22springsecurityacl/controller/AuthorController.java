package ru.geracimov.otus.spring.hw22springsecurityacl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.security.User;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;
import ru.geracimov.otus.spring.hw22springsecurityacl.exception.NotFoundException;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.AuthorService;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.BookService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    public final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/author")
    public String showAuthorList(@NotNull Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author-list";
    }

    @GetMapping("/author/add")
    public String showAuthorAddPage(Author author) {
        return "author-add";
    }

    @PostMapping("/author/add")
    public String authorSave(@Valid Author author,
                             @NotNull BindingResult result,
                             User user) {
        if (result.hasErrors()) {
            return "author-add";
        }
        authorService.save(author);
        return "redirect:/author";
    }

    @GetMapping("/author/{id}/edit")
    public String showAuthorEditPage(@PathVariable("id") Long id,
                                     @NotNull Model model) {
        Author author = authorService.getAuthorById(id)
                                     .orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "author-edit";
    }

    @PostMapping("/author/{id}/edit")
    public String updateAuthor(@PathVariable("id") Long id,
                               @Valid Author author,
                               @NotNull BindingResult result) {
        if (result.hasErrors()) {
            author.setId(id);
            return "author-edit";
        }
        authorService.save(author);
        return "redirect:/author";
    }

    @PostMapping("/author/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id)
                                     .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        authorService.delete(author);
        return "redirect:/author";
    }

    @GetMapping("/author/{id}/book")
    public String showAuthorDetailPage(@PathVariable("id") Long id,
                                       @NotNull Model model) {
        Author author = authorService.getAuthorById(id)
                                     .orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        model.addAttribute("books", bookService.getBooksByAuthorId(author.getId()));
        return "author-book";
    }

}