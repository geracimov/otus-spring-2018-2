package ru.geracimov.otus.spring.hw15libraryreact.services;

import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    Optional<Author> getAuthorById(UUID id);

    List<Author> getAllAuthors();

    List<Author> getAllAuthorsByBook(UUID bookId);

    boolean delete(UUID id);

    boolean delete(Author author);

    Author save(Author author);
}
