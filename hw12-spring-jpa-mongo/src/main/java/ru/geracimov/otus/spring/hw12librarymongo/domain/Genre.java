package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "genre")
@RequiredArgsConstructor
public class Genre {

    @Id
    private ObjectId id;

    private final String name;

}
