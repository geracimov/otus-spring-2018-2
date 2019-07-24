package ru.geracimov.otus.spring.hw12librarymongo.services;


import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;

import java.util.List;


public interface BookService {

    Book getBookById(ObjectId id);

    List<Book> getAllBooks();

    List<Book> getBooksByGenreId(ObjectId genreId);

    Book addBook(String name, int year, int pageCount, String isbn);

    void addGenreToBook(ObjectId genreId, ObjectId bookId);

    void addReviewToBook(String reviewerName, String text, ObjectId bookId);

    void delReviewFromBook(ObjectId reviewObjectId);

    int delete(ObjectId id);

}
