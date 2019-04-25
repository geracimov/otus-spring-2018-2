package ru.geracimov.otus.spring.hw10libraryjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Review;

import java.util.Set;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Set<Review> getReviewsByBook(Book book);
}
