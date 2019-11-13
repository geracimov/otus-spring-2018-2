package ru.geracimov.otus.spring.hw18webflux.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public Author(String name, Date birth) {
        this.name = name;
        this.birth = birth;
    }

}
