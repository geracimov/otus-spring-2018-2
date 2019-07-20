package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.exception.BookNotExistsExeption;
import ru.geracimov.otus.spring.hw12librarymongo.repository.BookRepository;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bRepo;

    @Override
    public Book getBookById(ObjectId id) {
        return bRepo.findById(id)
                    .orElseThrow(BookNotExistsExeption::new);
    }

    @Override
    public boolean delete(ObjectId id) {
        return false;
    }

    @Override
    public boolean delete(Book book) {
        return false;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public Book addBook(String name,
                        int year,
                        int pageCount,
                        String isbn) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bRepo.findAll();
    }

    @Override
    public Set<Book> getBooksByAuthor(Author author) {
        return null;
    }

    @Override
    public void addGenreToBook(ObjectId genreId,
                               ObjectId bookId) {

    }

    @Override
    public void addGenreToBook(Genre genre,
                               Book book) {

    }

    @Override
    public void addAuthorToBook(ObjectId authorId,
                                ObjectId bookId) {

    }

    @Override
    public void addAuthorToBook(Author author,
                                Book book) {

    }

    @Override
    public void addReviewToBook(String reviewerName,
                                String text,
                                ObjectId bookId) {

    }

    @Override
    public void addReviewToBook(Review review,
                                Book book) {

    }

    @Override
    public void delReviewFromBook(ObjectId reviewObjectId,
                                  ObjectId bookObjectId) {

    }

    @Override
    public List<Book> getAllContainsAndNotContainig(String contains,
                                                    String notContaining) {
        return null;
    }
}
