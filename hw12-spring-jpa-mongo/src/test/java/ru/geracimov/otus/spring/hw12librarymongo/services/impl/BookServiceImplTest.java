package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import ru.geracimov.otus.spring.hw12librarymongo.AbstractRepositoryTest;
import ru.geracimov.otus.spring.hw12librarymongo.services.BookService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookServiceImpl должен")
@SpringBootTest(classes = {ServiceTestConfiguration.class})
class BookServiceImplTest  {

    @Autowired BookService bService;

    @Test
    @Order(1)
    @DisplayName("сохранять книгу по ее реквизитам")
    void addBook() {
        val bookSaved = bService.addBook("newBookName", 2019, 1000, "ISBN000NUMBER");
        assertThat(bookSaved).hasFieldOrPropertyWithValue("name", "newBookName")
                             .hasFieldOrPropertyWithValue("year", 2019)
                             .hasFieldOrPropertyWithValue("pageCount", 1000)
                             .hasFieldOrPropertyWithValue("isbn", "ISBN000NUMBER");
    }

    @Test
    @Order(2)
    @DisplayName("получать все книги")
    void getAllBooks() {
        val books=bService.getAllBooks();
        assertThat(books).hasSize(1);
    }


}