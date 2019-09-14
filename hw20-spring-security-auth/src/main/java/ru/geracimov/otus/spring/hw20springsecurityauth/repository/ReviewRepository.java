package ru.geracimov.otus.spring.hw20springsecurityauth.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends PagingAndSortingRepository<Review, UUID> {
    List<Review> getReviewsByBook(Book book);
}
