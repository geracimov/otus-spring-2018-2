package ru.geracimov.otus.spring.hw15libraryreact.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Book;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends PagingAndSortingRepository<Review, UUID> {

    List<Review> getReviewsByBook(Book book);

}
