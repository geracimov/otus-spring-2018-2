package ru.geracimov.otus.spring.hw12librarymongo.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.repository.GenreRepositoryCustom;

@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void removeGenreFromAllBooksByGenreId(ObjectId id) {
        val query = Query.query(Criteria.where("$id")
                                        .is(id));
        val update = new Update().pull("genres", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

}
