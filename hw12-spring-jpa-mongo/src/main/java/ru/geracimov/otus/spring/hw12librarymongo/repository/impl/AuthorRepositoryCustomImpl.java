package ru.geracimov.otus.spring.hw12librarymongo.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.repository.AuthorRepositoryCustom;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorsFromAllBooksByAuthorId(ObjectId id) {
        val query = Query.query(Criteria.where("$id")
                                        .is(id));
        val update = new Update().pull("authors", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public List<Author> getAuthorsByBookId(ObjectId bookId) {
        val aggregation = newAggregation(
                match(Criteria.where("id")
                              .is(bookId)),
                unwind("authors"),
                project().andExclude("_id")
                         .and("authors.id")
                         .as("_id")
                         .and("name")
                         .as("name")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class)
                            .getMappedResults();
    }

}
