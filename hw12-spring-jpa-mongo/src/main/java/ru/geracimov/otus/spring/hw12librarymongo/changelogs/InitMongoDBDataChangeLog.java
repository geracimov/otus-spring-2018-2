package ru.geracimov.otus.spring.hw12librarymongo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<Author> authors;
    private List<Genre> genres;

    @ChangeSet(order = "001", id = "createAuthors", author = "geracimov")
    public void createAuthors(MongoTemplate mongo) {
        authors = IntStream.rangeClosed(1, 10)
                           .mapToObj(i -> new Author("AuthorName" + i,
                                                     LocalDate.now()
                                                              .plus(i, ChronoUnit.DAYS)))
                           .collect(Collectors.toList());
        mongo.insertAll(authors);
    }

    @ChangeSet(order = "002", id = "createGenres", author = "geracimov")
    public void createGenres(MongoTemplate mongo) {
        genres = IntStream.rangeClosed(1, 10)
                          .mapToObj(i -> new Genre("GenreName" + i))
                          .collect(Collectors.toList());
        mongo.insertAll(genres);
    }

    @ChangeSet(order = "003", id = "createBooks", author = "geracimov")
    public void createBooks(MongoTemplate mongo) {
        Random rand = new Random();

        val books = IntStream.rangeClosed(1, 10)
                             .mapToObj(i -> new Book(null, "BookName" + i,
                                                     rand.nextInt(500) + 1500,
                                                     rand.nextInt(200) + 500,
                                                     "ISBN" + i,
                                                     getRandomElements(authors, rand, 5),
                                                     getRandomElements(genres, rand, 10), new ArrayList<>()))
                             .collect(Collectors.toList());

        mongo.insertAll(books);
    }


    private <T> List<T> getRandomElements(List<T> list,
                                         Random rand,
                                         int n) {
        val result = new ArrayList<T>();
        for (int i = 0; i < rand.nextInt(n); i++) {
            result.add(list.get(rand.nextInt(list.size())));
        }
        return result;
    }
}
