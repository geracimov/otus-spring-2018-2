package ru.geracimov.otus.spring.hw10libraryjpa.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.GenreRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.services.GenreService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);
    private final GenreRepository genreRepository;

    public GenreServiceImpl(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getGenreById(UUID uuid) {
        return this.genreRepository.getOne(uuid);
    }

    public Set<Genre> getGenresByBook(Book book) {
        return this.genreRepository.getGenresByBooks(book);
    }

    public List<Genre> getAllGenres() {
        return this.genreRepository.findAll();
    }

    public Genre addGenre(String name) {
        try {
            Genre genre = new Genre(name);
            return this.genreRepository.saveAndFlush(genre);
        } catch (Exception var3) {
            log.error("Error adding genre", var3);
            return null;
        }
    }

    public boolean delete(UUID id) {
        try {
            this.genreRepository.deleteById(id);
            return true;
        } catch (Exception var3) {
            log.error("Error deleting genre - " + id, var3);
            return false;
        }
    }

    public boolean delete(Genre genre) {
        return this.delete(genre.getId());
    }

    public void save(Genre genre) {
        this.genreRepository.saveAndFlush(genre);
    }
}
