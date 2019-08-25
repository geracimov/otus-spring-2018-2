package ru.geracimov.otus.spring.hw18webflux.repository.impl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl  //implements AuthorRepositoryCustom
{
//   private final ReactiveMongoTemplate mongoTemplate;
//
//    public void removeAuthorsFromAllBooksByAuthorId(String id) {
//        val query = ReactiveQuQuery.query(Criteria.where("$id")
//                                        .is(id));
//        val update = new Update().pull("authors", query);
//        mongoTemplate.updateMulti(new Query(), update, Book.class);
//    }
//
//    public Flux<Author> getAuthorsByBookId(ObjectId bookId) {
//        val aggregation = newAggregation(
//                match(Criteria.where("id")
//                              .is(bookId)),
//                unwind("authors"),
//                project().andExclude("_id")
//                         .and("authors.id")
//                         .as("_id")
//                         .and("name")
//                         .as("name")
//        );
////        return mongoTemplate.aggregate(aggregation, Book.class, Author.class)
////                            .getMappedResults();
//        return null;


}
