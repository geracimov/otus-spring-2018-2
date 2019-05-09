package ru.geracimov.otus.spring.hw14librarymvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Book;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Review;

import java.util.Set;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Set<Review> getReviewsByBook(Book book);
}
