package ru.geracimov.otus.spring.hw12librarymongo;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

@DataMongoTest
@ComponentScan({
        "ru.geracimov.otus.spring.hw12librarymongo.repository",
        "ru.geracimov.otus.spring.hw12librarymongo.config"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractRepositoryTest {
}
