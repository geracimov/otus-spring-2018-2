package ru.geracimov.otus.spring.hw18webflux.domain.mapper;


import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw18webflux.domain.Review;
import ru.geracimov.otus.spring.hw18webflux.domain.dto.ReviewDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public Review toReview(ReviewDto dto) {
        return new Review(dto.getReviewerName(),
                          dto.getDateTime(),
                          dto.getText());
    }

    public ReviewDto toReviewDto(Review Review) {
        return new ReviewDto(Review.getId(), Review.getReviewerName(), Review.getDateTime(), Review.getText());
    }

    public List<ReviewDto> toReviewDtoList(Collection<Review> Reviews) {
        return Reviews.stream()
                      .map(this::toReviewDto)
                      .collect(Collectors.toList());
    }

}
