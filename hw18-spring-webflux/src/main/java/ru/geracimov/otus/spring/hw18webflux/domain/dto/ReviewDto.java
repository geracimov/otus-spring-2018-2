package ru.geracimov.otus.spring.hw18webflux.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDto {

    private String id;

    private String reviewerName;

    private LocalDateTime dateTime;

    private String text;

    @JsonCreator
    public ReviewDto(@JsonProperty("reviewerName") String reviewerName,
                     @JsonProperty("dateTime") LocalDateTime dateTime,
                     @JsonProperty("text") String text) {
        this.reviewerName = reviewerName;
        this.dateTime = dateTime;
        this.text = text;
    }

}
