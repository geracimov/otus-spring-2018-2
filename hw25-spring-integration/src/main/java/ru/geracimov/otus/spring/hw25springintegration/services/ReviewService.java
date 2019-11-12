package ru.geracimov.otus.spring.hw25springintegration.services;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.geracimov.otus.spring.hw25springintegration.domain.Review;

public interface ReviewService {

    Review getReviewById(Long id);

    Iterable<Review> getAllReviews();

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') " +
            "or hasPermission(#id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review', 'DELETE')")
    boolean delete(Long id);

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') " +
            "or hasPermission(#review.id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review', 'DELETE')")
    boolean delete(Review review);

    void save(Review review);

}
