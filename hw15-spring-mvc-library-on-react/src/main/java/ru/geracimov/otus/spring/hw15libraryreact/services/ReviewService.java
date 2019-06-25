package ru.geracimov.otus.spring.hw15libraryreact.services;

import ru.geracimov.otus.spring.hw15libraryreact.domain.Book;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Review;

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
