package ru.geracimov.otus.spring.hw12librarymongo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.repository.BookRepositoryCustom;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void addGenreToBook(ObjectId genreId,
                               ObjectId bookId) {
        var upd = mongoTemplate.updateFirst(
                Query.query(Criteria.where("id")
                                    .is(bookId)),
                new Update().push("genres", genreId), Book.class);
    }

    @Override
    public void addReviewToBook(Review review, ObjectId id) {
        var query = new Query();
        query.addCriteria(Criteria.where("id")
                                  .is(id));
        var update = new Update().push("reviews", review);
        System.out.println(mongoTemplate.updateFirst(query, update, Book.class));
    }

}
