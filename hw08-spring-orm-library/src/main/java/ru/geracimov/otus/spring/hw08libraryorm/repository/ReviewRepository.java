package ru.geracimov.otus.spring.hw08libraryorm.repository;

import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review> {

    List<Review> getReviewsByBook(Book book);

    List<Review> getAllReviews();
}
