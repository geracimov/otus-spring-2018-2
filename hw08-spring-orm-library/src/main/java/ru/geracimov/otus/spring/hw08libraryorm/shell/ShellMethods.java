package ru.geracimov.otus.spring.hw08libraryorm.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw08libraryorm.services.AuthorService;
import ru.geracimov.otus.spring.hw08libraryorm.services.BookService;
import ru.geracimov.otus.spring.hw08libraryorm.services.GenreService;
import ru.geracimov.otus.spring.hw08libraryorm.services.ReviewService;

import java.time.LocalDate;
import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ShellMethods {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ReviewService reviewService;

    ////////////////////////////// books ///////////////////////////////
    @ShellMethod(value = "Print all books", group = "books", key = "book-all")
    public void printAllBooks() {
        bookService.getAllBooks()
                   .forEach(System.out::println);
    }

    @ShellMethod(value = "Add new book", group = "books", key = "book-add")
    public void addNewBook(@ShellOption String name,
                           @ShellOption int year,
                           @ShellOption int pagecount,
                           @ShellOption String isbn) {
        bookService.addBook(name, year, pagecount, isbn);
    }

    @ShellMethod(value = "Delete book", group = "books", key = "book-del")
    public void delBook(@ShellOption UUID uuid) {
        bookService.delete(uuid);
    }

    @ShellMethod(value = "Add review to book", group = "books", key = "book-add-review")
    public void addReviewToBook(@ShellOption String reviewername,
                                @ShellOption String text,
                                @ShellOption UUID bookUuid) {
        bookService.addReviewToBook(reviewername, text, bookUuid);
    }

    @ShellMethod(value = "Del review from book", group = "books", key = "book-del-review")
    public void delReviewFromBook(@ShellOption UUID reviewUuid,
                                  @ShellOption UUID bookUuid) {
        bookService.delReviewFromBook(reviewUuid, bookUuid);
    }

    @ShellMethod(value = "Add genre to book", group = "books", key = "book-add-genre")
    public void addGenreToBook(@ShellOption UUID genreUuid,
                               @ShellOption UUID bookUuid) {
        bookService.addGenreToBook(genreUuid, bookUuid);
    }

    ////////////////////////////// authors ///////////////////////////////
    @ShellMethod(value = "Print all authors", group = "authors", key = "author-all")
    public void printAllAuthors() {
        authorService.getAllAuthors()
                     .forEach(System.out::println);
    }

    @ShellMethod(value = "Add new author", group = "authors", key = "author-add")
    public void addNewAuthor(@ShellOption String name,
                             @ShellOption LocalDate birth) {
        authorService.addAuthor(name, birth);
    }

    @ShellMethod(value = "Delete author", group = "authors", key = "author-del")
    public void delAuthor(@ShellOption UUID uuid) {
        authorService.delete(uuid);
    }

    ////////////////////////////// reviews ///////////////////////////////
    @ShellMethod(value = "Print all reviews", group = "reviews", key = "review-all")
    public void printAllReviews() {
        reviewService.getAllReviews()
                     .forEach(System.out::println);
    }

    @ShellMethod(value = "Print reviews by book", group = "reviews", key = "review-book")
    public void printReviewsByBook(UUID uuid) {
        reviewService.getReviewsByBook(bookService.getBookById(uuid))
                     .forEach(System.out::println);
    }

    ////////////////////////////// genres ///////////////////////////////
    @ShellMethod(value = "Print all genres", group = "genres", key = "genre-all")
    public void printAllGenres() {
        genreService.getAllGenres()
                    .forEach(System.out::println);
    }

    @ShellMethod(value = "Print genres by book", group = "genres", key = "genre-book")
    public void printGenresByBook(UUID bookUuid) {
        genreService.getGenresByBook(bookUuid)
                    .forEach(System.out::println);
    }

    @ShellMethod(value = "Add new genre", group = "genres", key = "genre-add")
    public void addNewGenre(@ShellOption String name) {
        genreService.addGenre(name);
    }

    @ShellMethod(value = "Delete genre", group = "genres", key = "genre-del")
    public void delGenre(@ShellOption UUID uuid) {
        genreService.delete(uuid);
    }
}