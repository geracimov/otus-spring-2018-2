package ru.geracimov.otus.spring.hw22springsecurityacl.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    List<Review> getReviewsByBook(Book book);
}
