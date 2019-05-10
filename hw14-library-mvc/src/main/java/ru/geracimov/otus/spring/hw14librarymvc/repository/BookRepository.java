package ru.geracimov.otus.spring.hw14librarymvc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends PagingAndSortingRepository<Book, UUID> {
    List<Book> findBooksByAuthors(Author author);

    List<Book> findBooksAndGenresByAuthors(Author author);

    @Query("select b from Book b inner join b.authors a where a.id = :authorId")
    List<Book> findBooksByAuthor_Id(UUID authorId);

    @Query("select b, a, g from Book b left outer join b.authors a left outer join b.genres g where a.id = :authorId")
    List<Object> findBooksAuthorsGenresByAuthor_Id(UUID authorId);

    List<Book> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                           String notContainig);
}
