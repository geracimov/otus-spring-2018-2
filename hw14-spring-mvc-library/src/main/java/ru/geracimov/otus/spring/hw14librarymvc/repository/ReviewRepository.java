package ru.geracimov.otus.spring.hw14librarymvc.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Book;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends PagingAndSortingRepository<Review, UUID> {
    List<Review> getReviewsByBook(Book book);
}
