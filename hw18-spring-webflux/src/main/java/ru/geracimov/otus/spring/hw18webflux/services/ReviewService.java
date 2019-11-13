package ru.geracimov.otus.spring.hw18webflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Review;

public interface ReviewService {

    Flux<Review> getAllReviews();

    Mono<Void> deleteReview(String reviewId);

    Mono<Review> getReviewById(String reviewId);

}
