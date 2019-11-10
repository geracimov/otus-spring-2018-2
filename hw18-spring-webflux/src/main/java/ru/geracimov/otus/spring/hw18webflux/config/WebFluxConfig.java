package ru.geracimov.otus.spring.hw18webflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;
import ru.geracimov.otus.spring.hw18webflux.services.AuthorService;
import ru.geracimov.otus.spring.hw18webflux.services.BookService;
import ru.geracimov.otus.spring.hw18webflux.services.GenreService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebFluxConfig {

    @Bean
    public RouterFunction<ServerResponse> htmlRouterRoot(@Value("classpath:/public/index.html") Resource html) {
        return route(GET("/"), request
                -> ok().contentType(MediaType.TEXT_HTML)
                       .syncBody(html)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> htmlRouterJS(@Value("classpath:/public/bundle.min.js") Resource html) {
        return route(GET("/bundle.min.js"), request
                -> ok().contentType(MediaType.TEXT_HTML)
                       .syncBody(html)
        );
    }

    @Bean("bookController")
    public RouterFunction<ServerResponse> bookEndpoint(BookService bookService) {
        return route()
                .GET("/api/book", request -> ok()
                        .body(bookService.getAllBooks(), Book.class))
                .GET("/api/book/{id}", request -> ok()
                        .body(bookService.getBookById(request.pathVariable("id")), Book.class))
                .POST("/api/book", request -> ok()
                        .body(request.bodyToMono(Book.class)
                                     .flatMap(bookService::saveBook), Book.class))
                .PUT("/api/book/{id}", request -> ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(request.bodyToMono(Book.class)
                                     .flatMap(book -> {
                                         book.setId(request.pathVariable("id"));
                                         return Mono.just(book);
                                     })
                                     .flatMap(bookService::saveBook), Book.class))
                .DELETE("/api/book/{id}", request -> ok()
                        .body(bookService.delBookById(request.pathVariable("id")), Void.class))
                .build();
    }

    @Bean("genreController")
    public RouterFunction<ServerResponse> genreEndpoint(GenreService genreService) {
        return route()
                .GET("/api/genre", request -> ok()
                        .body(genreService.getAllGenres(), Genre.class))
                .GET("/api/genre/{id}", request -> ok()
                        .body(genreService.getGenreById(request.pathVariable("id")), Genre.class))
                .POST("/api/genre", request -> ok()
                        .body(request.bodyToMono(Genre.class)
                                     .flatMap(genreService::saveGenre), Genre.class))
                .PUT("/api/genre/{id}", request -> ok()
                        .body(request.bodyToMono(Genre.class)
                                     .flatMap(genre -> {
                                         genre.setId(request.pathVariable("id"));
                                         return Mono.just(genre);
                                     })
                                     .flatMap(genreService::saveGenre), Genre.class))
                .DELETE("/api/genre/{id}", request -> ok()
                        .body(genreService.deleteGenre(request.pathVariable("id")), Void.class))
                .build();
    }

    @Bean("authorController")
    public RouterFunction<ServerResponse> authorEndpoint(AuthorService authorService) {
        return route()
                .GET("/api/author", request -> ok()
                        .body(authorService.getAllAuthors(), Author.class))
                .GET("/api/author/{id}", request -> ok()
                        .body(authorService.getAuthorById(request.pathVariable("id")), Author.class))
                .POST("/api/author", request -> ok()
                        .body(request.bodyToMono(Author.class)
                                     .flatMap(authorService::save), Author.class))
                .PUT("/api/author/{id}", request -> ok()
                        .body(request.bodyToMono(Author.class)
                                     .flatMap(author -> {
                                         author.setId(request.pathVariable("id"));
                                         return Mono.just(author);
                                     })
                                     .flatMap(authorService::save), Author.class))
                .DELETE("/api/author/{id}", request -> ok()
                        .body(authorService.deleteAuthorById(request.pathVariable("id")), Void.class))
                .build();
    }

}
