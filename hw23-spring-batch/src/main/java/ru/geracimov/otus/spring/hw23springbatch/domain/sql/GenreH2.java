package ru.geracimov.otus.spring.hw23springbatch.domain.sql;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name = "Genre")
@Table(name = "GENRE")
@ToString(of = {"id", "name"})
public class GenreH2 {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

}