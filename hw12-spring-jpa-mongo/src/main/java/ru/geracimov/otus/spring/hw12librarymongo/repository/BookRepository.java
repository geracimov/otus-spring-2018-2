package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Book;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, ObjectId>, BookRepositoryCustom {

    List<Book> findAllByGenresContaining(Genre genre);

    int deleteBookById(ObjectId id);

    @Query("{'reviews.id': ?0}")
    List<Book> findAllByReviewsContaining(ObjectId id);

}
