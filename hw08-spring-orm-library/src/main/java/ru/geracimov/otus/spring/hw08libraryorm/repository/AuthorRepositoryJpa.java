package ru.geracimov.otus.spring.hw08libraryorm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;

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
public class AuthorRepositoryJpa implements AuthorRepository {
    private final CrudRepository<Author> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Author author) {
        crudRepository.create(author);
    }

    @Override
    public Author read(Class<Author> clazz,
                       UUID uuid) {
        return crudRepository.read(clazz, uuid);
    }

    @Override
    public void update(Author author) {
        crudRepository.update(author);
    }

    @Override
    public void delete(Author author) {
        crudRepository.delete(author);
    }

    @Override
    public Set<Book> getBooksByAuthor(Author author) {

        TypedQuery<Book> typedQuery = em.createQuery("SELECT b FROM Book b   WHERE :author member of b.authors",
                                                     Book.class);
        typedQuery.setParameter("author", author);
        return typedQuery.getResultStream()
                         .collect(Collectors.toSet());
    }

    @Override
    public Set<Book> getBooksByAuthor(UUID uuid) {

        TypedQuery<Book> typedQuery = em.createQuery("SELECT b FROM Book b   WHERE :author member of b.authors",
                                                     Book.class);
        typedQuery.setParameter("author", uuid);
        return typedQuery.getResultStream()
                         .collect(Collectors.toSet());
    }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }
}
