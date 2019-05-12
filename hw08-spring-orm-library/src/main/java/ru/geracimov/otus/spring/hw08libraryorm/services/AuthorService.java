package ru.geracimov.otus.spring.hw08libraryorm.services;

import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AuthorService {
    Author getAuthorById(UUID uuid);

    List<Author> getAllAuthors();

    Set<Book> getBooksByAuthor(Author author);

    Set<Book> getBooksByAuthor(UUID uuid);

    Author addAuthor(String name,
                     LocalDate birth);

    boolean delete(UUID id);

    boolean delete(Author author);
}
