package ru.geracimov.otus.spring.hw12librarymongo.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;
import ru.geracimov.otus.spring.hw12librarymongo.services.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreMethods {
    private final BookService bookService;
    private final GenreService genreService;

    @ShellMethod(value = "Add new genre", group = "genres", key = {"genre-add"})
    public void addNewGenre(@ShellOption String name) {
        var genre = genreService.saveGenre(name);
        System.out.println("New genre added with id=" + genre.getId());
    }

    @ShellMethod(value = "Delete genre", group = "genres", key = {"genre-del"})
    public void delGenre(@ShellOption ObjectId id) {
        genreService.deleteGenre(id);
        System.out.println("Deleted id=" + id);
    }

    @ShellMethod(value = "Print genres by <bookId>", group = "genres", key = {"genre-find-byBook"})
    public void printGenresByBook(ObjectId bookId) {
        System.out.println("Search result:");
        var genres = bookService.getBookById(bookId)
                                .getGenres();
        genres.forEach(System.out::println);
        System.out.println("Total:" + genres.size());
    }

    @ShellMethod(value = "Print all genres with setting page number/size", group = "genres", key = {"genres"})
    public void printAllGenres(@ShellOption int pageNumber,
                               @ShellOption int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("name")));
        val page = genreService.getAllGenres(pageable);
        page.forEach(System.out::println);
        System.out.println("TotalElements: " + page.getTotalElements());
        System.out.println("TotalPages: " + page.getTotalPages());
    }

}
