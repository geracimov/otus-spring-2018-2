package ru.geracimov.otus.spring.hw10libraryjpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.BookRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.GenreRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.services.GenreService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public Genre getGenreById(UUID uuid) {
        return genreRepository.read(Genre.class, uuid);
    }

    @Override
    public Set<Genre> getGenresByBook(UUID bookUuid) {
        Book book = bookRepository.read(Book.class, bookUuid);
        return genreRepository.getGenresByBook(book);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

    @Override
    public Genre addGenre(String name) {
        try {
            Genre genre = new Genre(name);
            genreRepository.create(genre);
            return genre;
        } catch (Exception e) {
            log.error("Error adding genre", e);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Genre genre = genreRepository.read(Genre.class, id);
        return delete(genre);
    }

    @Override
    public boolean delete(Genre genre) {
        try {
            genreRepository.delete(genre);
        } catch (Exception e) {
            log.error("Error deleting genre - " + genre.getId(), e);
            return false;
        }
        return true;
    }

}
