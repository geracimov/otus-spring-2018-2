package ru.geracimov.otus.spring.hw15libraryreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("select b from Author b inner join b.books a where a.id = :bookId")
    List<Author> findAuthorsByBook(UUID bookId);


    Author findAuthorByName(String name);
}
