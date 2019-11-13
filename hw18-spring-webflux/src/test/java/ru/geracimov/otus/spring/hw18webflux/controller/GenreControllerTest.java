package ru.geracimov.otus.spring.hw18webflux.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.config.WebFluxConfig;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;
import ru.geracimov.otus.spring.hw18webflux.services.AuthorService;
import ru.geracimov.otus.spring.hw18webflux.services.BookService;
import ru.geracimov.otus.spring.hw18webflux.services.GenreService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = WebFluxConfig.class)
public class GenreControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    GenreService genreService;

    @MockBean
    BookService bookService;

    @MockBean
    AuthorService authorService;

    @MockBean
    ReactiveMongoTemplate reactiveMongoTemplate;

    private static Genre GENRE1 = new Genre("GENRE1");
    private static Genre GENRE2 = new Genre("GENRE2");
    private static Genre GENRE3 = new Genre("GENRE3");


    @Test
    void getAllGenres() throws Exception {
        when(genreService.getAllGenres()).thenReturn(Flux.just(GENRE1, GENRE3, GENRE2));

        webTestClient.get()
                     .uri("/api/genre")
                     .accept(MediaType.APPLICATION_JSON_UTF8)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectHeader()
                     .contentType(MediaType.APPLICATION_JSON_UTF8)
                     .expectBodyList(Genre.class)
                     .contains(GENRE1, GENRE2, GENRE3);

        verify(genreService).getAllGenres();
    }

    @Test
    void getGenreById() {
        when(genreService.getGenreById("ID1")).thenReturn(Mono.just(GENRE1));

        webTestClient.get()
                     .uri("/api/genre/ID1")
                     .accept(MediaType.APPLICATION_JSON_UTF8)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectHeader()
                     .contentType(MediaType.APPLICATION_JSON_UTF8)
                     .expectBody(Genre.class)
                     .isEqualTo(GENRE1);
    }

}