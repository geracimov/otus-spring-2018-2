package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "book")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Id
    private ObjectId id;

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

    @Override
    public String toString() {
        return String.format("-%s-  '%s' %s / %s / %s    [%s]   [%s]",
                             id, name, year, pageCount, isbn,
                             authors.stream()
                                    .map(Author::getName)
                                    .collect(Collectors.joining(",")),
                             genres.stream()
                                   .map(Genre::getName)
                                   .collect(Collectors.joining(",")));
    }

}
