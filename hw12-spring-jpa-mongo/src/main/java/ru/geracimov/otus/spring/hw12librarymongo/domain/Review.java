package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "review")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "book")
public class Review {

    @Id
    private ObjectId id;

    private String reviewerName;

    private LocalDateTime dateTime;

    private String text;

    private Book book;

    public Review(String reviewerName,
                  LocalDateTime dateTime,
                  String text) {
        this.reviewerName = reviewerName;
        this.dateTime = dateTime;
        this.text = text;
    }

}
