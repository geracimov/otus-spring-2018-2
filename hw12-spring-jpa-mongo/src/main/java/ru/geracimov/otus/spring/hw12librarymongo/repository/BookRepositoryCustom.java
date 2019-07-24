package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;


public interface BookRepositoryCustom {

    void addGenreToBook(ObjectId genreId, ObjectId bookId);

    void addReviewToBook(Review review, ObjectId id);

}
