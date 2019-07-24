package ru.geracimov.otus.spring.hw12librarymongo.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellMethods {
    private final BookService bookService;

    @ShellMethod(value = "Add new book by <name>, <year>, <pageCount>, <isbn>", group = "books", key = {"book-add"})
    public String addNewBook(@ShellOption String name,
                             @ShellOption int year,
                             @ShellOption int pagecount,
                             @ShellOption String isbn) {
        var savedBook = bookService.addBook(name, year, pagecount, isbn);
        return "Book saved successfully with id=" + savedBook.getId();
    }

    @ShellMethod(value = "Add genre to book", group = "books", key = {"book-add-genre"})
    public String addNewBook(@ShellOption ObjectId genreId,
                             @ShellOption ObjectId bookId) {
        bookService.addGenreToBook(genreId, bookId);
        return "Book saved successfully with id=" + bookId;
    }

    @ShellMethod(value = "Delete book", group = "books", key = {"book-del"})
    public String delBook(@ShellOption ObjectId id) {
        int deleted = bookService.delete(id);
        return "Count of deleted books: " + deleted;
    }

    @ShellMethod(value = "Add review to book", group = "books", key = {"book-add-review"})
    public String addReviewToBook(@ShellOption String reviewername,
                                  @ShellOption String text,
                                  @ShellOption ObjectId bookId) {
        this.bookService.addReviewToBook(reviewername, text, bookId);
        return "Book saved successfully with id=" + bookId;
    }

    @ShellMethod(value = "Print books by <genre>", group = "books", key = {"book-find-byGenre"})
    public String printBooksByGenreId(@ShellOption ObjectId genreId) {
        System.out.println("Search result:");
        List<Book> books = this.bookService.getBooksByGenreId(genreId);
        books.forEach(System.out::println);
        return "Total: " + books.size();
    }

    @ShellMethod(value = "Print one book by <id>", group = "books", key = {"book-find-byId"})
    public void printBookById(ObjectId id) {
        System.out.println("Search result:");
        System.out.println(bookService.getBookById(id));
    }

    @ShellMethod(value = "Print all books", group = "books", key = {"books"})
    public String printAllBooks() {
        var books = bookService.getAllBooks();
        books.forEach(System.out::println);
        return "Total: " + books.size();
    }

}
