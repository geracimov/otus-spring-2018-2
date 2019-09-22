package ru.geracimov.otus.spring.hw22springsecurityacl.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"authors", "genres", "reviews"})
@Table(name = "BOOK")
public class Book {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    Set<Author> authors = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GENRE_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID"))
    Set<Genre> genres = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "PAGE_COUNT")
    private int pageCount;
    @Column(name = "ISBN")
    private String isbn;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Book(String name,
                int year,
                int pageCount,
                String isbn) {
        this.name = name;
        this.year = year;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

}
