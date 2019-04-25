package ru.geracimov.otus.spring.hw10libraryjpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.ReviewRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.services.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review getReviewById(UUID uuid) {
        return reviewRepository.read(Review.class, uuid);
    }

    @Override
    public List<Review> getReviewsByBook(Book book) {
        return reviewRepository.getReviewsByBook(book);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }


    @Override
    public Review addReview(String reviewerName, LocalDateTime dateTime, String text) {
        try {
            Review review = new Review(reviewerName, dateTime, text);
            reviewRepository.create(review);
            return review;
        } catch (Exception e) {
            log.error("Error adding review", e);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Review review = reviewRepository.read(Review.class, id);
        return delete(review);
    }

    @Override
    public boolean delete(Review review) {
        try {
            reviewRepository.delete(review);
        } catch (Exception e) {
            log.error("Error deleting review - " + review.getId(), e);
            return false;
        }
        return true;
    }

}
