package ru.geracimov.otus.spring.hw08libraryorm.services;

import ru.geracimov.otus.spring.hw08libraryorm.domain.Genre;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GenreService {
    Genre getGenreById(UUID uuid);

    Set<Genre> getGenresByBook(UUID bookUuid);

    List<Genre> getAllGenres();

    Genre addGenre(String name);

    boolean delete(UUID id);

    boolean delete(Genre genre);
}
