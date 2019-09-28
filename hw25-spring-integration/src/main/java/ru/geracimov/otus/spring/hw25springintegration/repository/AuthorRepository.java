package ru.geracimov.otus.spring.hw25springintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw25springintegration.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
