package ru.geracimov.otus.spring.hw18webflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;

public interface BookService {

    Mono<Book> getBookById(String id);

    Mono<Void> delBookById(String id);

    Flux<Book> getAllBooks();

    Flux<Book> getBooksByGenreId(String genreId);

    Mono<Book> addBook(String name, int year, int pageCount, String isbn);

    Mono<Book> saveBook(Book book);

    Mono<Void> addGenreToBook(String genreId, String bookId);

    Mono<Void> addReviewToBook(String reviewerName, String text, String bookId);

    Mono<Void> delReviewFromBook(String reviewId);

}
