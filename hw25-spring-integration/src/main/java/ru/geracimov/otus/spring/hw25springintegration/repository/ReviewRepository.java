package ru.geracimov.otus.spring.hw25springintegration.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw25springintegration.domain.Book;
import ru.geracimov.otus.spring.hw25springintegration.domain.Review;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    List<Review> getReviewsByBook(Book book);
}
