package ru.geracimov.otus.spring.hw20springsecurityauth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "book")
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "REVIEWER_NAME")
    private String reviewerName;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Review(String reviewerName,
                  LocalDateTime dateTime,
                  String text) {
        this.reviewerName = reviewerName;
        this.dateTime = dateTime;
        this.text = text;
    }

}
