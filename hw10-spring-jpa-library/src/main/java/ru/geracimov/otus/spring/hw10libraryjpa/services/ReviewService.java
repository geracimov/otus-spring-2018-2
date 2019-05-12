package ru.geracimov.otus.spring.hw10libraryjpa.services;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReviewService {
    Review getReviewById(UUID id);

    Set<Review> getReviewsByBook(Book book);

    List<Review> getAllReviews();

    Review addReview(String reviwerName, LocalDateTime dateTime, String text);

    boolean delete(UUID id);

    boolean delete(Review review);

    void save(Review review);
}
