package ru.geracimov.otus.spring.hw14librarymvc.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Genre;
import ru.geracimov.otus.spring.hw14librarymvc.exception.NotFoundException;
import ru.geracimov.otus.spring.hw14librarymvc.services.AuthorService;
import ru.geracimov.otus.spring.hw14librarymvc.services.BookService;
import ru.geracimov.otus.spring.hw14librarymvc.services.GenreService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public String showAuthorList(@NotNull Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("allGenres", genres);
        return "genre-list";
    }

    @GetMapping("/genre/add")
    public String showAuthorAddPage(Genre genre) {
        return "genre-add";
    }

    @PostMapping("/genre/add")
    public String authorSave(@Valid Genre genre,
                             @NotNull BindingResult result) {
        if (result.hasErrors()) {
            return "genre-add";
        }
        genreService.save(genre);
        return "redirect:/genre";
    }

    @GetMapping("/genre/{id}/edit")
    public String showAuthorEditPage(@PathVariable("id") UUID id,
                                     @NotNull Model model) {
        Genre genre = genreService.getGenreById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genre-edit";
    }

    @PostMapping("/genre/{id}/edit")
    public String updateAuthor(@PathVariable("id") UUID id,
                               @Valid Genre genre,
                               @NotNull BindingResult result) {
        if (result.hasErrors()) {
            genre.setId(id);
            return "genre-edit";
        }
        genreService.save(genre);
        return "redirect:/genre";
    }

    @GetMapping("/genre/{id}/delete")
    public String deleteUser(@PathVariable("id") UUID id) {
        Genre genre = genreService.getGenreById(id).orElseThrow(() -> new IllegalArgumentException("Invalid genre Id:" + id));
        genreService.delete(genre);
        return "redirect:/genre";
    }

}