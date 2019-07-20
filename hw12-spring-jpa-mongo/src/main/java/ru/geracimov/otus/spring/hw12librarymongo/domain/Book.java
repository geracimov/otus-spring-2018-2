package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.*;
import org.bson.types.ObjectId;
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
public class Book {

    @Id
    private ObjectId id;

    @NonNull
    private String name;

    @NonNull
    private int year;

    @NonNull
    private int pageCount;

    private String isbn;

    @DBRef
    private List<Author> authors = new ArrayList<>();

    private List<Genre> genres = new ArrayList<>();

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
