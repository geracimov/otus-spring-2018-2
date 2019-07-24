package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.exception.BookNotExistsExeption;
import ru.geracimov.otus.spring.hw12librarymongo.repository.BookRepository;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;
import ru.geracimov.otus.spring.hw12librarymongo.services.GenreService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bRepo;
    private final GenreService gService;

    @Override
    public Book getBookById(ObjectId id) {
        return bRepo.findById(id)
                    .orElseThrow(BookNotExistsExeption::new);
    }

    @Override
    public int delete(ObjectId id) {
        return bRepo.deleteBookById(id);
    }

    @Override
    public Book addBook(String name, int year, int pageCount, String isbn) {
        var book = new Book(name, year, pageCount, isbn);
        return bRepo.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bRepo.findAll();
    }

    @Override
    public void addGenreToBook(ObjectId genreId, ObjectId bookId) {
        bRepo.addGenreToBook(genreId, bookId);
    }

    @Override
    public void addReviewToBook(String reviewerName, String text, ObjectId bookId) {
        var review = new Review(reviewerName, LocalDateTime.now(), text);
        bRepo.addReviewToBook(review, bookId);
    }

    @Override
    public void delReviewFromBook(ObjectId reviewId) {
        bRepo.findAllByReviewsContaining(reviewId)
             .forEach(System.out::println);
    }

    @Override
    public List<Book> getBooksByGenreId(ObjectId genreId) {
        var genre = gService.getGenreById(genreId);
        return bRepo.findAllByGenresContaining(genre);
    }
}
