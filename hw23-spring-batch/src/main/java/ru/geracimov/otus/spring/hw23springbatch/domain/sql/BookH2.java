package ru.geracimov.otus.spring.hw23springbatch.domain.sql;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Entity(name = "Book")
@NoArgsConstructor
@ToString(of = {"id", "name", "year", "pageCount", "isbn"})
@Table(name = "BOOK")
public class BookH2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = AuthorH2.class)
    @JoinTable(name = "AUTHOR_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    private Set<AuthorH2> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GENRE_BOOK",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID"))
    private Set<GenreH2> genres = new HashSet<>();

}