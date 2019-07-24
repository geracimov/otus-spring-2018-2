package ru.geracimov.otus.spring.hw08libraryorm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Genre;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Review;
import ru.geracimov.otus.spring.hw08libraryorm.repository.BookRepository;
import ru.geracimov.otus.spring.hw08libraryorm.repository.GenreRepository;
import ru.geracimov.otus.spring.hw08libraryorm.repository.ReviewRepository;
import ru.geracimov.otus.spring.hw08libraryorm.services.BookService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final GenreRepository genreRepository;

    @Override
    public Book getBookById(UUID uuid) {
        return bookRepository.read(Book.class, uuid);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Set<Book> getBooksByAuthor(Author author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public Book addBook(String name,
                        int year,
                        int pageCount,
                        String isbn) {
        try {
            Book book = new Book(name, year, pageCount, isbn);
            bookRepository.create(book);
            return book;
        } catch (Exception e) {
            log.error("Error adding book", e);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Book book = bookRepository.read(Book.class, id);
        return delete(book);
    }

    @Override
    public boolean delete(Book book) {
        try {
            bookRepository.delete(book);
        } catch (Exception e) {
            log.error("Error deleting book - " + book.getId(), e);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void addReviewToBook(String reviewerName,
                                String text,
                                UUID bookUuid) {
        Review review = new Review(reviewerName, LocalDateTime.now(), text);
        reviewRepository.create(review);
        Book book = bookRepository.read(Book.class, bookUuid);
        book.addReview(review);
        bookRepository.update(book);
    }

    @Override
    @Transactional
    public void delReviewFromBook(UUID reviewUuid,
                                  UUID bookUuid) {
        Review review = reviewRepository.read(Review.class, reviewUuid);
        Book book = bookRepository.read(Book.class, bookUuid);
        book.delReview(review);
        bookRepository.update(book);
    }

    @Override
    public void addGenreToBook(UUID genreUuid,
                               UUID bookUuid) {
        Genre genre = genreRepository.read(Genre.class, genreUuid);
        Book book = bookRepository.read(Book.class, bookUuid);
        book.delGenre(genre);
        bookRepository.update(book);
    }


}
