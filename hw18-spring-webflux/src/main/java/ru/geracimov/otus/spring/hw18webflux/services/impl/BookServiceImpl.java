package ru.geracimov.otus.spring.hw18webflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.repository.BookRepository;
import ru.geracimov.otus.spring.hw18webflux.services.BookService;
import ru.geracimov.otus.spring.hw18webflux.services.GenreService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bRepo;
    private final GenreService gService;

    @Override
    public Mono<Book> getBookById(String id) {
        return bRepo.findById(id);
    }

    @Override
    public Mono<Void> delBookById(String id) {
        return bRepo.deleteById(id);
    }

    @Override
    public Flux<Book> getAllBooks() {
        return bRepo.findAll();
    }

    @Override
    public Flux<Book> getBooksByGenreId(String genreId) {
        return bRepo.findAllByGenresContaining(gService.getGenreById(genreId));
    }

    @Override
    public Mono<Book> addBook(String name, int year, int pageCount, String isbn) {
        Book book = new Book(name, year, pageCount, isbn);
        return bRepo.save(book);
    }

    @Override
    public Mono<Book> saveBook(Book book) {
        return bRepo.save(book);
    }

    @Override
    public Mono<Void> addGenreToBook(String genreId, String bookId) {
        return null;
    }

    @Override
    public Mono<Void> addReviewToBook(String reviewerName, String text, String bookId) {
        return null;
    }

    @Override
    public Mono<Void> delReviewFromBook(String reviewId) {
        return null;
    }

}
