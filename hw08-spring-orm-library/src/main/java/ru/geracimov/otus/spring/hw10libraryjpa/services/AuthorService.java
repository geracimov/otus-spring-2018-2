package ru.geracimov.otus.spring.hw10libraryjpa.services;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AuthorService {
    Author getAuthorById(UUID uuid);

    List<Author> getAllAuthors();

    Set<Book> getBooksByAuthor(Author author);

    Set<Book> getBooksByAuthor(UUID uuid);

    Author addAuthor(String name, LocalDate birth);

    boolean delete(UUID id);

    boolean delete(Author author);
}
