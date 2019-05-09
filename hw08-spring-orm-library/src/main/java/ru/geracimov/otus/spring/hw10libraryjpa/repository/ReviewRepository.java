package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review> {

    List<Review> getReviewsByBook(Book book);

    List<Review> getAllReviews();
}
