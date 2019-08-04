package ru.geracimov.otus.spring.hw08libraryorm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
@RequiredArgsConstructor
public class ReviewRepositoryJpa implements ReviewRepository {
    private final CrudRepository<Review> crudRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Review review) {
        crudRepository.create(review);
    }

    @Override
    public Review read(Class<Review> clazz,
                       UUID uuid) {
        return crudRepository.read(clazz, uuid);
    }

    @Override
    public void update(Review review) {
        crudRepository.update(review);
    }

    @Override
    public void delete(Review review) {
        crudRepository.delete(review);
    }

    @Override
    public List<Review> getReviewsByBook(Book book) {
        TypedQuery<Review> typedQuery = em.createQuery("select b from Review b  where  b.book = :book ", Review.class);
        typedQuery.setParameter("book", book);
        return typedQuery.getResultList();
    }

    @Override
    public List<Review> getAllReviews() {
        TypedQuery<Review> query = em.createQuery("select g from Review g", Review.class);
        return query.getResultList();
    }
}
