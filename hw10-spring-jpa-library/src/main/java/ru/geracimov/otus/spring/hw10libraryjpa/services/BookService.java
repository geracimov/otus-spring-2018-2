package ru.geracimov.otus.spring.hw10libraryjpa.services;


import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BookService {
    Book getBookById(UUID uuid);

    boolean delete(UUID id);

    boolean delete(Book book);

    void save(Book book);

    Book addBook(String name, int year, int pageCount, String isbn);

    List<Book> getAllBooks();

    Set<Book> getBooksByAuthor(Author author);

    void addGenreToBook(UUID genreId, UUID bookId);

    void addGenreToBook(Genre genre, Book book);

    void addAuthorToBook(UUID authorId, UUID bookId);

    void addAuthorToBook(Author author, Book book);

    void addReviewToBook(String reviewerName, String text, UUID bookId);

    void addReviewToBook(Review review, Book book);

    void delReviewFromBook(UUID reviewUuid, UUID bookUuid);

    List<Book> getAllContainsAndNotContainig(String contains, String notContaining);
}
