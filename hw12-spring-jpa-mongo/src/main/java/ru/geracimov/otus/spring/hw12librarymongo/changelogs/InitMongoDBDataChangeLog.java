package ru.geracimov.otus.spring.hw12librarymongo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<Author> authors;
    private List<Genre> genres;

    @ChangeSet(order = "001", id = "createAuthors", author = "geracimov")
    public void createAuthors(MongoTemplate mongo) {
        var dostoevsky = new Author("Достоевский", LocalDate.of(1821, 11, 11));
        var chehov = new Author("Чехов", LocalDate.of(1860, 1, 29));
        var tolstoy = new Author("Толстой", LocalDate.of(1883, 1, 10));
        authors = List.copyOf(mongo.insertAll(List.of(dostoevsky, chehov, tolstoy)));

        Set<Author> authors2 = IntStream.rangeClosed(1, 100000)
                                       .mapToObj(i -> new Author("AuthorName" + i,
                                                                 LocalDate.now()
                                                                          .plus(i, ChronoUnit.DAYS)))
                                       .collect(Collectors.toSet());
        mongo.insertAll(authors2);
    }

    @ChangeSet(order = "002", id = "createGenres", author = "geracimov")
    public void createGenres(MongoTemplate mongo) {
        var fantastic = new Genre("Фантастика");
        var scary = new Genre("Ужасы");
        var novell = new Genre("Роман");
        genres = List.copyOf(mongo.insertAll(List.of(fantastic, scary, novell)));
    }

    @ChangeSet(order = "003", id = "createBooks", author = "geracimov")
    public void createBooks(MongoTemplate mongo) {
        var idiot = new Book("Идиот", 1378, 121);
        idiot.addAuthor(authors.get(0))
             .addAuthor(authors.get(1));
        idiot.addGenre(genres.get(0))
             .addGenre(genres.get(1));

        var vSad = new Book("Вишневый сад", 1233, 11)
                .addAuthor(authors.get(1))
                .addGenre(genres.get(0));

        var custom = new Book("Custom", 1311, 1211)
                .addAuthors(authors.subList(0, 2))
                .addGenres(genres.subList(0, 2));

        mongo.insertAll(List.of(idiot, vSad, custom));
    }

}
