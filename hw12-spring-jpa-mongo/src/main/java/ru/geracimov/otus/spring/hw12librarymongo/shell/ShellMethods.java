package ru.geracimov.otus.spring.hw12librarymongo.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.services.AuthorService;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;
import ru.geracimov.otus.spring.hw12librarymongo.services.GenreService;
import ru.geracimov.otus.spring.hw12librarymongo.services.ReviewService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ShellComponent
@RequiredArgsConstructor
public class ShellMethods {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final ReviewService reviewService;

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
    public void printContANotContB(@ShellOption String a,
                                   @ShellOption String b) {
        List<Book> books = this.bookService.getAllContainsAndNotContainig(a, b);
        books.forEach(System.out::println);
    }

    @ShellMethod(
            value = "Add new book",
            group = "books",
            key = {"book-add"}
    )
    public void addNewBook(@ShellOption String name,
                           @ShellOption int year,
                           @ShellOption int pagecount,
                           @ShellOption String isbn) {
        this.bookService.addBook(name, year, pagecount, isbn);
    }

    @ShellMethod(
            value = "Delete book",
            group = "books",
            key = {"book-del"}
    )
    public void delBook(@ShellOption ObjectId uuid) {
        this.bookService.delete(uuid);
    }

    @ShellMethod(
            value = "Add review to book",
            group = "books",
            key = {"book-add-review"}
    )
    public void addReviewToBook(@ShellOption String reviewername,
                                @ShellOption String text,
                                @ShellOption ObjectId bookUuid) {
        this.bookService.addReviewToBook(reviewername, text, bookUuid);
    }

    @ShellMethod(
            value = "Del review from book",
            group = "books",
            key = {"book-del-review"}
    )
    public void delReviewFromBook(@ShellOption ObjectId reviewUuid,
                                  @ShellOption ObjectId bookUuid) {
        this.bookService.delReviewFromBook(reviewUuid, bookUuid);
    }

    @ShellMethod(
            value = "Add genre to book",
            group = "books",
            key = {"book-add-genre"}
    )
    public void addGenreToBook(@ShellOption ObjectId genreUuid,
                               @ShellOption ObjectId bookUuid) {
        this.bookService.addGenreToBook(genreUuid, bookUuid);
    }

    @ShellMethod(
            value = "Print all authors",
            group = "authors",
            key = {"author-all"}
    )
    public void printAllAuthors() {
        List<Author> authors = this.authorService.getAll();
        authors.forEach(System.out::println);
    }


    @ShellMethod(
            value = "Print one author by id",
            group = "authors",
            key = {"author-one"}
    )
    public void printAuthorById(ObjectId id) {
        System.out.println(authorService.getById(id));
    }

    @ShellMethod(
            value = "Add new author",
            group = "authors",
            key = {"author-add"}
    )
    public void addNewAuthor(@ShellOption String name,
                             @ShellOption LocalDate birth) {
        this.authorService.save(name, birth);
    }

    @ShellMethod(
            value = "Delete author",
            group = "authors",
            key = {"author-del"}
    )
    public void delAuthor(@ShellOption ObjectId uuid) {
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
    public void printReviewsByBook(ObjectId uuid) {
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
    public void printGenresByBook(ObjectId bookUuid) {
        this.bookService.getBookById(bookUuid)
                        .getGenres()
                        .forEach(System.out::println);
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
    public void delGenre(@ShellOption ObjectId uuid) {
        this.genreService.delete(uuid);
    }
}
