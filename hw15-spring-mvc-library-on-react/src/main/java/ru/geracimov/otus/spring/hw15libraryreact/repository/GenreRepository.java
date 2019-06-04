package ru.geracimov.otus.spring.hw15libraryreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query("select b from Genre b inner join b.books a where a.id = :bookId")
    List<Genre> findGenresByBook(UUID bookId);

}
