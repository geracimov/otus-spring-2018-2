package ru.geracimov.otus.spring.hw10libraryjpa.services;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Author getAuthorById(UUID id);

    List<Author> getAllAuthors();

    Author addAuthor(String name, LocalDate birth);

    boolean delete(UUID id);

    boolean delete(Author author);

    void save(Author author);
}
