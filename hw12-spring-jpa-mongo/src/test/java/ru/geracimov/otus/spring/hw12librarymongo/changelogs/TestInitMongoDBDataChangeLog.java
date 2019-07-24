package ru.geracimov.otus.spring.hw12librarymongo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeLog(order = "001")
public class TestInitMongoDBDataChangeLog {

    private List<Author> authors = new ArrayList<>(3);
    private List<Genre> genres = new ArrayList<>(3);
    private List<Book> books = new ArrayList<>(3);


    @ChangeSet(order = "000", id = "dropDB", author = "geracimov", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "createAuthors", author = "geracimov")
    public void createAuthors(MongoTemplate mongo) {
        authors = IntStream.rangeClosed(1, 3)
                           .mapToObj(i -> new Author("AuthorTest" + i,
                                                     LocalDate.now()
                                                              .plus(i, ChronoUnit.DAYS)))
                           .collect(Collectors.toList());
        mongo.insertAll(authors);
    }

    @ChangeSet(order = "002", id = "createGenres", author = "geracimov")
    public void createGenres(MongoTemplate mongo) {
        genres = IntStream.rangeClosed(1, 3)
                          .mapToObj(i -> new Genre("GenreTest" + i))
                          .collect(Collectors.toList());
        mongo.insertAll(genres);
    }

    @ChangeSet(order = "003", id = "createBooks", author = "geracimov")
    public void createBooks(MongoTemplate mongo) {

        val books = IntStream.rangeClosed(1, 5)
                             .mapToObj(i -> new Book(null,
                                                     "BookTest" + i,
                                                     2019,
                                                     100,
                                                     "ISBN" + i,
                                                     List.of(authors.get(i % 3)),
                                                     List.of(genres.get(i % 3)),
                                                     List.of(new Review("ReviewerTest" + i,
                                                                        LocalDateTime.now(),
                                                                        "review text test" + i))))
                             .collect(Collectors.toList());

        mongo.insertAll(books);
    }
}
