package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "book")
@NoArgsConstructor
public class Book {

    @Id
    private UUID id;

    private String name;

    private int year;

    private int pageCount;

    private String isbn;

    private List<Review> reviews;

    List<Author> authors = new ArrayList<>();

    List<Genre> genres = new ArrayList<>();

    public Book(String name,
                int year,
                int pageCount,
                String isbn) {
        this.name = name;
        this.year = year;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.addBook(this);
    }

    public void delAuthor(Author author) {
        authors.remove(author);
        author.removeBook(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.addBook(this);
    }

    public void delGenre(Genre genre) {
        genres.remove(genre);
        genre.removeBook(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setBook(this);
    }

    public void delReview(Review review) {
        reviews.remove(review);
        review.setBook(null);
    }
}
