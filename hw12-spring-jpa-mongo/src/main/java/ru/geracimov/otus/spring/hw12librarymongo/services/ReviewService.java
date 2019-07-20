package ru.geracimov.otus.spring.hw12librarymongo.services;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ReviewService {

    Review getReviewById(ObjectId id);

    Set<Review> getReviewsByBook(Book book);

    List<Review> getAllReviews();

    Review addReview(String reviwerName,
                     LocalDateTime dateTime,
                     String text);

    boolean delete(ObjectId id);

    boolean delete(Review review);

    void save(Review review);

}
