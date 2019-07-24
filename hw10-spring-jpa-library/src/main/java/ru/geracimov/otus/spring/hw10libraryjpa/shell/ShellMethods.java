package ru.geracimov.otus.spring.hw10libraryjpa.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;
import ru.geracimov.otus.spring.hw10libraryjpa.services.AuthorService;
import ru.geracimov.otus.spring.hw10libraryjpa.services.BookService;
import ru.geracimov.otus.spring.hw10libraryjpa.services.GenreService;
import ru.geracimov.otus.spring.hw10libraryjpa.services.ReviewService;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@ShellComponent
public class ShellMethods {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ReviewService reviewService;

    public ShellMethods(final BookService bookService, final AuthorService authorService, final GenreService genreService, final ReviewService reviewService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.reviewService = reviewService;
    }

    @ShellMethod(
            value = "Print all books",
            group = "books",
            key = {"book-all"}
    )
    public void printAllBooks() {
        List<Book> allBooks = this.bookService.getAllBooks();
        allBooks.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Print books contains A and not containing B",
            group = "books",
            key = {"book-contA-notcontB"}
    )
    public void printContANotContB(@ShellOption String a, @ShellOption String b) {
        List<Book> books = this.bookService.getAllContainsAndNotContainig(a, b);
        books.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Add new book",
            group = "books",
            key = {"book-add"}
    )
    public void addNewBook(@ShellOption String name, @ShellOption int year, @ShellOption int pagecount, @ShellOption String isbn) {
        this.bookService.addBook(name, year, pagecount, isbn);
    }

    @ShellMethod(
            value = "Delete book",
            group = "books",
            key = {"book-del"}
    )
    public void delBook(@ShellOption UUID uuid) {
        this.bookService.delete(uuid);
    }

    @ShellMethod(
            value = "Add review to book",
            group = "books",
            key = {"book-add-review"}
    )
    public void addReviewToBook(@ShellOption String reviewername, @ShellOption String text, @ShellOption UUID bookUuid) {
        this.bookService.addReviewToBook(reviewername, text, bookUuid);
    }

    @ShellMethod(
            value = "Del review from book",
            group = "books",
            key = {"book-del-review"}
    )
    public void delReviewFromBook(@ShellOption UUID reviewUuid, @ShellOption UUID bookUuid) {
        this.bookService.delReviewFromBook(reviewUuid, bookUuid);
    }

    @ShellMethod(
            value = "Add genre to book",
            group = "books",
            key = {"book-add-genre"}
    )
    public void addGenreToBook(@ShellOption UUID genreUuid, @ShellOption UUID bookUuid) {
        this.bookService.addGenreToBook(genreUuid, bookUuid);
    }

    @ShellMethod(
            value = "Print all authors",
            group = "authors",
            key = {"author-all"}
    )
    public void printAllAuthors() {
        List<Author> authors = this.authorService.getAllAuthors();
        authors.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Add new author",
            group = "authors",
            key = {"author-add"}
    )
    public void addNewAuthor(@ShellOption String name, @ShellOption LocalDate birth) {
        this.authorService.addAuthor(name, birth);
    }

    @ShellMethod(
            value = "Delete author",
            group = "authors",
            key = {"author-del"}
    )
    public void delAuthor(@ShellOption UUID uuid) {
        this.authorService.delete(uuid);
    }

    @ShellMethod(
            value = "Print all reviews",
            group = "reviews",
            key = {"review-all"}
    )
    public void printAllReviews() {
        List<Review> allReviews = this.reviewService.getAllReviews();
        allReviews.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Print reviews by book",
            group = "reviews",
            key = {"review-book"}
    )
    public void printReviewsByBook(UUID uuid) {
        Set<Review> reviewsByBook = this.reviewService.getReviewsByBook(this.bookService.getBookById(uuid));
        reviewsByBook.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Print all genres",
            group = "genres",
            key = {"genre-all"}
    )
    public void printAllGenres() {
        List<Genre> allGenres = this.genreService.getAllGenres();
        allGenres.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Print genres by book",
            group = "genres",
            key = {"genre-book"}
    )
    public void printGenresByBook(UUID bookUuid) {
        Book book = this.bookService.getBookById(bookUuid);
        Set<Genre> genresByBook = this.genreService.getGenresByBook(book);
        genresByBook.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Add new genre",
            group = "genres",
            key = {"genre-add"}
    )
    public void addNewGenre(@ShellOption String name) {
        this.genreService.addGenre(name);
    }

    @ShellMethod(
            value = "Delete genre",
            group = "genres",
            key = {"genre-del"}
    )
    public void delGenre(@ShellOption UUID uuid) {
        this.genreService.delete(uuid);
    }
}
