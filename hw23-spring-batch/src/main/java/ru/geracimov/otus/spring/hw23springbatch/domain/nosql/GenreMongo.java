package ru.geracimov.otus.spring.hw23springbatch.domain.nosql;

import lombok.Builder;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "genre")
public class GenreMongo {
    @Id
    private final ObjectId id;
    @NonNull
    private final String name;

}
