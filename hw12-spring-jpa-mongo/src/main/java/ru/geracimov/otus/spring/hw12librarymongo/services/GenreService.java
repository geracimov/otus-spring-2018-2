package ru.geracimov.otus.spring.hw12librarymongo.services;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;

import java.util.List;
import java.util.Set;


public interface GenreService {

    Genre getGenreById(ObjectId id);

    Set<Genre> getGenresByBook(Book book);

    List<Genre> getAllGenres();

    Genre addGenre(String name);

    boolean delete(ObjectId id);

    boolean delete(Genre genre);

    void save(Genre genre);

}