package ru.geracimov.otus.spring.hw12librarymongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Document(collection = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private ObjectId id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birth;

    public Author(String name,
                  LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

}
