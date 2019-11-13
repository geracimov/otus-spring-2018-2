package ru.geracimov.otus.spring.hw18webflux.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.repository.BookRepositoryCustom;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Void> removeAuthorFromBookById(String id) {
        Query query = Query.query(Criteria.where("id")
                                          .is(new ObjectId(id)));
        Update update = new Update().pull("authors", new BasicDBObject("$id", new ObjectId(id)));
        Mono<UpdateResult> updateResult = mongoTemplate.updateMulti(new Query(), update, Book.class);
        return updateResult.then();
    }

}
