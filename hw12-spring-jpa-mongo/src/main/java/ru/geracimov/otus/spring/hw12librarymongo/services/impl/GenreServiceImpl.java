package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.services.GenreService;

import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public Genre getGenreById(ObjectId id) {
        return null;
    }

    @Override
    public Set<Genre> getGenresByBook(Book book) {
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }

    @Override
    public Genre addGenre(String name) {
        return null;
    }

    @Override
    public boolean delete(ObjectId id) {
        return false;
    }

    @Override
    public boolean delete(Genre genre) {
        return false;
    }

    @Override
    public void save(Genre genre) {

    }
}
