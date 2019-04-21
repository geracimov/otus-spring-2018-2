package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Set<Book> findBooksByAuthors(Author author);

    List<Book> findAllByNameContainsAndNameIsNotContaining(String contains, String notContainig);
}
