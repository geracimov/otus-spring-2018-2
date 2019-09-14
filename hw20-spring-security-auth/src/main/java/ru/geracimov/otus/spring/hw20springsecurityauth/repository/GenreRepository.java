package ru.geracimov.otus.spring.hw20springsecurityauth.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    List<Genre> getGenresByBooks(Book book);

    List<Genre> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                            String nonContaining);
}
