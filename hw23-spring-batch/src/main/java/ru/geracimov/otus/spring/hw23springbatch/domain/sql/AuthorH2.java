package ru.geracimov.otus.spring.hw23springbatch.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "Author")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHOR")
@ToString(of = {"id", "name", "birth"})
public class AuthorH2 {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BIRTH")
    private LocalDate birth;

}