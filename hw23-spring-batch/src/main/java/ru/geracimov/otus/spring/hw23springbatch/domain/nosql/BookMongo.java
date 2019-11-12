package ru.geracimov.otus.spring.hw23springbatch.domain.nosql;

import lombok.Builder;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Builder
@Document(collection = "book")
public class BookMongo {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private int year;
    private int pageCount;
    private String isbn;

    @DBRef
    private Set<AuthorMongo> authors = new HashSet<>();

    @DBRef
    private Set<GenreMongo> genres = new HashSet<>();

}
