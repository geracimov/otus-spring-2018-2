package ru.geracimov.otus.spring.hw18webflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.domain.Review;
import ru.geracimov.otus.spring.hw18webflux.domain.dto.ReviewDto;
import ru.geracimov.otus.spring.hw18webflux.services.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bService;

    @GetMapping("/api/book")
    public Flux<Book> getAllBooks() {
        return bService.getAllBooks();
    }

    @GetMapping("/api/book/{id}")
    public Mono<Book> getBook(@PathVariable("id") String id) {
        return bService.getBookById(id);
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bService.delBookById(id);
    }

    @PostMapping("/api/book")
    public Mono<Book> addBook(@RequestBody Book book) {
        return bService.saveBook(book);
    }

    @PutMapping("/api/book/{id}")
    public Mono<Book> updateBook(@RequestBody Book book, @PathVariable String id) {
        book.setId(id);
        return bService.saveBook(book);
    }

    @PostMapping(value = "/api/book/{id}/review")
    public Mono<Book> addReviewToBook(@RequestBody ReviewDto dto, @PathVariable("id") String bookId) {


        Review review = new Review();
        review.setDateTime(dto.getDateTime());
        review.setReviewerName(dto.getReviewerName());
        review.setText(dto.getText());

        return bService.getBookById(bookId)
                       .doOnNext(b -> b.getReviews()
                                       .add(review))
                       .flatMap(bService::saveBook);

    }


}