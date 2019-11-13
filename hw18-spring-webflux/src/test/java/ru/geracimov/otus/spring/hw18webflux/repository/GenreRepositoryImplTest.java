package ru.geracimov.otus.spring.hw18webflux.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import reactor.test.StepVerifier;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class GenreRepositoryImplTest {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    private static Genre GENRE1 = new Genre("GENRE1");
    private static Genre GENRE2 = new Genre("GENRE2");
    private static Genre GENRE3 = new Genre("GENRE3");
    private static Genre GENRE4 = new Genre("GENRE3");


    @Test
    void testInsert() {
        genreRepository.save(GENRE1)
                       .map(Genre::getId)
                       .subscribe(savedId ->
                                          StepVerifier.create(genreRepository.findById(savedId))
                                                      .expectSubscription()
                                                      .assertNext(actual -> {
                                                          assertThat(actual.getId()).isNotNull();
                                                          assertThat(actual).isEqualToIgnoringGivenFields(GENRE1, "id");
                                                      })
                                                      .verifyComplete()
                       );
    }

}