package ru.geracimov.otus.spring.hw25springintegration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geracimov.otus.spring.hw25springintegration.domain.Genre;
import ru.geracimov.otus.spring.hw25springintegration.exception.NotFoundException;
import ru.geracimov.otus.spring.hw25springintegration.services.BookService;
import ru.geracimov.otus.spring.hw25springintegration.services.GenreService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final BookService bookService;
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
    public String showAuthorEditPage(@PathVariable("id") Long id,
                                     @NotNull Model model) {
        Genre genre = genreService.getGenreById(id)
                                  .orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genre-edit";
    }

    @PostMapping("/genre/{id}/edit")
    public String updateAuthor(@PathVariable("id") Long id,
                               @Valid Genre genre,
                               @NotNull BindingResult result) {
        if (result.hasErrors()) {
            genre.setId(id);
            return "genre-edit";
        }
        genreService.save(genre);
        return "redirect:/genre";
    }

    @PostMapping("/genre/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenreById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("Invalid genre Id:" + id));
        genreService.delete(genre);
        return "redirect:/genre";
    }

    @GetMapping("/genre/{id}/book")
    public String showGenreDetailPage(@PathVariable("id") Long id,
                                      @NotNull Model model) {
        Genre genre = genreService.getGenreById(id)
                                  .orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        model.addAttribute("books", bookService.getBooksByGenreId(genre.getId()));
        return "genre-book";
    }

}