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
public class BookRepositoryJpa implements BookRepository {
    private final CrudRepository<Book> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) {
        crudRepository.create(book);
    }

    @Override
    public Book read(Class<Book> clazz,
                     UUID uuid) {
        return crudRepository.read(clazz, uuid);
    }

    @Override
    public void update(Book book) {
        crudRepository.update(book);
    }

    @Override
    public void delete(Book book) {
        crudRepository.delete(book);
    }

    @Override
    public Set<Book> getBooksByAuthor(Author author) {
        TypedQuery<Book> typedQuery =
                em.createQuery("select b from Book b  where  :author MEMBER of b.authors", Book.class);
        typedQuery.setParameter("author", author);
        return typedQuery.getResultStream()
                         .collect(Collectors.toSet());
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }


}
