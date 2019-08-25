package ru.geracimov.otus.spring.hw18webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
public class LibraryWebFluxApp {

    public static void main(String[] args) {
        SpringApplication.run(LibraryWebFluxApp.class);
    }
//
//
//    @Bean
//    RouterFunction<ServerResponse> composedRoutes(ReviewMapper rMapper, BookRepository bRepo) {
//
////        PersonHandler handler = new PersonHandler(repository);
//
//        return route()
//                .GET("/func/person", accept(APPLICATION_JSON), handler::list)
//                .GET("/func/person/{id}", accept(APPLICATION_JSON),
//                     request -> repository.findById(request.pathVariable("id"))
//                                          .flatMap(person -> ok().contentType(APPLICATION_JSON)
//                                                                 .body(fromObject(person)))
//                )
//.POST("/api/book/{id}/review",
//      req -> req.body(BodyExtractors.toMono(ReviewDto.class))
//                .map(rMapper::toReview)
//.doOnNext(bRepo.findById(req.pathVariable("id")))
//                .doOnNext(repository::save)
//                .then(ok().build()))
//.build();
//    }

//    static class PersonHandler {
//
//        private PersonRepository repository;
//
//        PersonHandler(PersonRepository repository) {
//            this.repository = repository;
//        }
//
//        Mono<ServerResponse> list(ServerRequest request) {
//            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Person.class);
//        }
//    }
}
