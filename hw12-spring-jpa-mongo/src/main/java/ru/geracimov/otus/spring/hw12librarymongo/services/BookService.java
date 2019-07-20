package ru.geracimov.otus.spring.hw12librarymongo.services;


import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;

import java.util.List;
import java.util.Set;


public interface BookService {

    Book getBookById(ObjectId id);

    boolean delete(ObjectId id);

    boolean delete(Book book);

    void save(Book book);

    Book addBook(String name,
                 int year,
                 int pageCount,
                 String isbn);

    List<Book> getAllBooks();

    Set<Book> getBooksByAuthor(Author author);

    void addGenreToBook(ObjectId genreId,
                        ObjectId bookId);

    void addGenreToBook(Genre genre,
                        Book book);

    void addAuthorToBook(ObjectId authorId,
                         ObjectId bookId);

    void addAuthorToBook(Author author,
                         Book book);

    void addReviewToBook(String reviewerName,
                         String text,
                         ObjectId bookId);

    void addReviewToBook(Review review,
                         Book book);

    void delReviewFromBook(ObjectId reviewObjectId,
                           ObjectId bookObjectId);

    List<Book> getAllContainsAndNotContainig(String contains,
                                             String notContaining);

}
