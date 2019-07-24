package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
