package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        this.id = new ObjectId();
        this.reviewerName = reviewerName;
        this.dateTime = dateTime;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("-%s-  '%s' %s / %s  [%s]",
                             id, book.getName(), reviewerName, dateTime, text);
    }

}
