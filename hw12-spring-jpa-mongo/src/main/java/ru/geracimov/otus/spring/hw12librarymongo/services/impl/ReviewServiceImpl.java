package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.services.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public List<Review> getReviewsByBook(Book book) {
        return book.getReviews();
    }

}
