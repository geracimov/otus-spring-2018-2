package ru.geracimov.otus.spring.hw12librarymongo.services;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;


public interface GenreService {

    Genre getGenreById(ObjectId id);


    Page<Genre> getAllGenres(Pageable pageable);

    Genre saveGenre(Genre genre);

    Genre saveGenre(String name);

    void deleteGenre(ObjectId id);

}