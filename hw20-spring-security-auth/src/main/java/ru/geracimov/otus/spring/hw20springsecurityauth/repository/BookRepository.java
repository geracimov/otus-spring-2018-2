package ru.geracimov.otus.spring.hw20springsecurityauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Author;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {


    //    @EntityGraph( attributePaths = {"genres"})
    @Query("select b from Book b join fetch b.authors a")
    List<Book> findAllBy();

    List<Book> findBooksByAuthors(Author author);

    List<Book> findBooksAndGenresByAuthors(Author author);

    @Query("select b from Book b inner join b.authors a where a.id = :authorId")
    List<Book> findBooksByAuthor_Id(UUID authorId);

    @Query("select b, a, g from Book b left outer join b.authors a left outer join b.genres g where a.id = :authorId")
    List<Object> findBooksAuthorsGenresByAuthor_Id(UUID authorId);

    List<Book> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                           String notContainig);
}
