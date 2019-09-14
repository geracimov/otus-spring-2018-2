package ru.geracimov.otus.spring.hw20springsecurityauth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"authors", "genres", "reviews"})
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GENRE_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID"))
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
