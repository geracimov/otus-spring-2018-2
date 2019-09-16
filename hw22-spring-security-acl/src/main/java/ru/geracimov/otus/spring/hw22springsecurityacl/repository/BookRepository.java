package ru.geracimov.otus.spring.hw22springsecurityacl.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    @EntityGraph(attributePaths = {"authors", "genres"})
    List<Book> findAllBy();

    List<Book> findBooksByAuthors(Author author);

    List<Book> findBooksAndGenresByAuthors(Author author);

    List<Book> findBooksAndGenresByGenres(Genre genre);

    @Query("select b from Book b inner join b.authors a where a.id = :authorId")
    List<Book> findBooksByAuthorId(Long authorId);

    @Query("select b, a, g from Book b left outer join b.authors a left outer join b.genres g where a.id = :authorId")
    List<Object> findBooksAuthorsGenresByAuthor_Id(Long authorId);

    List<Book> findAllByNameContainsAndNameIsNotContaining(String contains,
                                                           String notContainig);
}
