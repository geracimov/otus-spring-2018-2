package ru.geracimov.otus.spring.hw25springintegration.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.geracimov.otus.spring.hw25springintegration.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> getGenreById(Long uuid);

    List<Genre> getAllGenres();

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasPermission(#id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre','DELETE')")
    boolean delete(Long id);

    @PreAuthorize("hasAnyAuthority('ADMIN') or hasPermission(#genre.id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre','DELETE')")
    boolean delete(Genre genre);

    void save(Genre genre);

}