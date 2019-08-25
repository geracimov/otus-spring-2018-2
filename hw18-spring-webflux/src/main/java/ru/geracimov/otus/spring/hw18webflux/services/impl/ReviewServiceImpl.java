package ru.geracimov.otus.spring.hw18webflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Review;
import ru.geracimov.otus.spring.hw18webflux.repository.ReviewRepository;
import ru.geracimov.otus.spring.hw18webflux.services.ReviewService;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository rRepo;

    @Override
    public Flux<Review> getAllReviews() {
        return rRepo.findAll();
    }

    @Override
    public Mono<Void> deleteReview(String reviewId) {
        return rRepo.deleteById(reviewId);
    }

    @Override
    public Mono<Review> getReviewById(String reviewId) {
        return rRepo.findById(reviewId);
    }

}
