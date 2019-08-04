package ru.geracimov.otus.spring.hw10libraryjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;

import java.util.Set;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Set<Genre> getGenresByBooks(Book book);

    Set<Genre> findAllByNameContainsAndNameIsNotContaining(String contains, String nonContaining);
}
