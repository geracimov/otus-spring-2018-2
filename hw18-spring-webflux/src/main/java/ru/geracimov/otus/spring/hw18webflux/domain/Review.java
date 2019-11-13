package ru.geracimov.otus.spring.hw18webflux.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Review {

    private String id;

    private String reviewerName;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateTime;

    private String text;

    @JsonCreator
    public Review(String reviewerName,
                  LocalDateTime dateTime,
                  String text) {
        this.reviewerName = reviewerName;
        this.dateTime = dateTime;
        this.text = text;
    }

}
