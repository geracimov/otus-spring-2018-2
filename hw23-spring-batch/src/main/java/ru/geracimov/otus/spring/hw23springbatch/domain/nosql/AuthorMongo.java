package ru.geracimov.otus.spring.hw23springbatch.domain.nosql;

import lombok.Builder;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Document(collection = "author")
public class AuthorMongo {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;

}
