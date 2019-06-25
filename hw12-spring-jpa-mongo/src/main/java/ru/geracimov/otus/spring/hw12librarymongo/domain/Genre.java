package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "genre")
@NoArgsConstructor
@ToString(exclude = {"books", "id"})
public class Genre {

    @Id
    private UUID id;

    private String name;

    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
