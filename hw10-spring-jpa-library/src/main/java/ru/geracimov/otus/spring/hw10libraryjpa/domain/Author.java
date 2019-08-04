package ru.geracimov.otus.spring.hw10libraryjpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AUTHOR")
@ToString(exclude = "books")
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH")
    private LocalDate birth;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<Book> books;

    public Author(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
