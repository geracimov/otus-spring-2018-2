package ru.geracimov.otus.spring.hw22springsecurityacl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> getGenresByBooks(Book book);

    List<Genre> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                            String nonContaining);
}
