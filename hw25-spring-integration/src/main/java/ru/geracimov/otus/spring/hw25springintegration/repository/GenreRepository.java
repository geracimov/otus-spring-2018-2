package ru.geracimov.otus.spring.hw25springintegration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw25springintegration.domain.Book;
import ru.geracimov.otus.spring.hw25springintegration.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> getGenresByBooks(Book book);

    List<Genre> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                            String nonContaining);
}
