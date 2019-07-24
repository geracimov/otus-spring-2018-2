package ru.geracimov.otus.spring.hw12librarymongo.services;

import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsByBook(Book book);

}
