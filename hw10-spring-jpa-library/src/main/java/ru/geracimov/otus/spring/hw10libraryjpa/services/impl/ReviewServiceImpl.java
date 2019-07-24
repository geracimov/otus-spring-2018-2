package ru.geracimov.otus.spring.hw10libraryjpa.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.ReviewRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.services.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReviewById(UUID id) {
        return this.reviewRepository.getOne(id);
    }

    public Set<Review> getReviewsByBook(Book book) {
        return this.reviewRepository.getReviewsByBook(book);
    }

    public List<Review> getAllReviews() {
        return this.reviewRepository.findAll();
    }

    public Review addReview(String reviewerName, LocalDateTime dateTime, String text) {
        try {
            Review review = new Review(reviewerName, dateTime, text);
            this.reviewRepository.saveAndFlush(review);
            return review;
        } catch (Exception var5) {
            log.error("Error adding review", var5);
            return null;
        }
    }

    public boolean delete(UUID id) {
        Review review = this.getReviewById(id);
        return this.delete(review);
    }

    public boolean delete(Review review) {
        try {
            this.reviewRepository.delete(review);
            return true;
        } catch (Exception var3) {
            log.error("Error deleting review - " + review.getId(), var3);
            return false;
        }
    }

    public void save(Review review) {
        this.reviewRepository.saveAndFlush(review);
    }
}
