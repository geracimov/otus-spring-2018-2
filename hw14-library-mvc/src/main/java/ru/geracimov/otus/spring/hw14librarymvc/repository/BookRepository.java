package ru.geracimov.otus.spring.hw14librarymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findBooksByAuthors(Author author);

    @Query("select b from Book b inner join b.authors a where a.id = :authorId")
    List<Book> findBooksByAuthor_Id(UUID authorId);

    List<Book> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                           String notContainig);
}
