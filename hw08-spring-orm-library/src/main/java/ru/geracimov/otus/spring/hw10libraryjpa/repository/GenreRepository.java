package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends CrudRepository<Genre> {

    Set<Genre> getGenresByBook(Book book);

    List<Genre> getAllGenres();
}
