package ru.geracimov.otus.spring.hw20springsecurityauth.services;

import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
    Review getReviewById(UUID id);

    List<Review> getReviewsByBook(Book book);

    Iterable<Review> getAllReviews();

    Review addReview(String reviwerName,
                     LocalDateTime dateTime,
                     String text);

    boolean delete(UUID id);

    boolean delete(Review review);

    void save(Review review);
}
