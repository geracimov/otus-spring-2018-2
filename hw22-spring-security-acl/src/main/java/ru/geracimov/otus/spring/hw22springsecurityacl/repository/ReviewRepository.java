package ru.geracimov.otus.spring.hw22springsecurityacl.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends PagingAndSortingRepository<Review, UUID> {
    List<Review> getReviewsByBook(Book book);
}
