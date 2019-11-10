package ru.geracimov.otus.spring.hw18webflux.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.repository.GenreRepositoryCustom;

@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Void> removeGenreFromAllBooksByGenreId(String id) {
        val query = Query.query(Criteria.where("$id")
                                        .is(id));
        val update = new Update().pull("genres", query);
        return mongoTemplate.updateMulti(new Query(), update, Book.class).then();
    }

}
