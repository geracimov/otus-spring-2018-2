package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.services.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public Review getReviewById(ObjectId id) {
        return null;
    }

    @Override
    public Set<Review> getReviewsByBook(Book book) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public Review addReview(String reviwerName,
                            LocalDateTime dateTime,
                            String text) {
        return null;
    }

    @Override
    public boolean delete(ObjectId id) {
        return false;
    }

    @Override
    public boolean delete(Review review) {
        return false;
    }

    @Override
    public void save(Review review) {

    }
}
