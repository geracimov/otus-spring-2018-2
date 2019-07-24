package ru.geracimov.otus.spring.hw08libraryorm.services;

import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BookService {
    Book getBookById(UUID uuid);

    List<Book> getAllBooks();

    Set<Book> getBooksByAuthor(Author author);

    Book addBook(String name,
                 int year,
                 int pageCount,
                 String isbn);

    boolean delete(UUID id);

    boolean delete(Book book);

    void addReviewToBook(String reviewerName,
                         String text,
                         UUID bookUuid);

    void delReviewFromBook(UUID reviewUuid,
                           UUID bookUuid);

    void addGenreToBook(UUID genreUuid,
                        UUID bookUuid);
}
