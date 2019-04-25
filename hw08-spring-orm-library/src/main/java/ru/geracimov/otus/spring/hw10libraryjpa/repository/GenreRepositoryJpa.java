package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Genre;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.CrudRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {
    private final CrudRepository<Genre> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Genre genre) {
        crudRepository.create(genre);
    }

    @Override
    public Genre read(Class<Genre> clazz, UUID uuid) {
        return crudRepository.read(clazz, uuid);
    }

    @Override
    public void update(Genre genre) {
        crudRepository.update(genre);
    }

    @Override
    public void delete(Genre genre) {
        crudRepository.delete(genre);
    }


    @Override
    public Set<Genre> getGenresByBook(Book book) {
        TypedQuery<Genre> typedQuery = em.createQuery("select b.genres from Book b  where  b = :book ", Genre.class);
        typedQuery.setParameter("book", book);
        return typedQuery.getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

}
