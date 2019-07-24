package ru.geracimov.otus.spring.hw12librarymongo.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;
import ru.geracimov.otus.spring.hw12librarymongo.services.ReviewService;

@ShellComponent
@RequiredArgsConstructor
public class ReviewShellMethods {
    private final BookService bookService;
    private final ReviewService reviewService;

    @ShellMethod(value = "Print reviews by <bookId>", group = "reviews", key = {"review-find-byBook"})
    public void printReviewsByBook(ObjectId uuid) {
        val reviewsByBook = reviewService.getReviewsByBook(bookService.getBookById(uuid));
        reviewsByBook.forEach(System.out::println);
    }


    @ShellMethod(value = "Del review from book", group = "reviews", key = {"review-del"})
    public void delReviewFromBook(@ShellOption ObjectId reviewUuid) {
        bookService.delReviewFromBook(reviewUuid);
    }

}
