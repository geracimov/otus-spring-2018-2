package ru.geracimov.otus.spring.hw18webflux.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "book")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Book implements Cloneable {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private int year;

    @NonNull
    private int pageCount;

    @NonNull
    private String isbn;

    @DBRef
    private List<Author> authors = new ArrayList<>();

    @DBRef
    private List<Genre> genres = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    public Book(Book b) {
        this.id = b.id;
        this.name = b.name;
        this.isbn = b.isbn;
        this.year = b.year;
        this.authors = b.authors;
        this.genres = b.genres;
        this.pageCount = b.pageCount;
        this.reviews = b.reviews;
    }

    public Book addAuthor(Author author) {
        this.getAuthors()
            .add(author);
        return this;
    }

    public Book addAuthors(Collection<Author> authors) {
        this.getAuthors()
            .addAll(authors);
        return this;
    }

    public Book addGenre(Genre genre) {
        this.getGenres()
            .add(genre);
        return this;
    }

    public Book addGenres(Collection<Genre> genres) {
        this.getGenres()
            .addAll(genres);
        return this;
    }

}
