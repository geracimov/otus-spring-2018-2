package ru.geracimov.otus.spring.hw15libraryreact.services;

import ru.geracimov.otus.spring.hw15libraryreact.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreService {
    Optional<Genre> getGenreById(UUID uuid);

    List<Genre> getGenresByBook(UUID bookId);

    List<Genre> getAllGenres();

    Genre addGenre(String name);

    boolean delete(UUID id);

    boolean delete(Genre genre);

    void save(Genre genre);
}