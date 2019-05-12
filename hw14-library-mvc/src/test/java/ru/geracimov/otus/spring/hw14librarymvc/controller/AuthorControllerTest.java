package ru.geracimov.otus.spring.hw14librarymvc.controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;
import ru.geracimov.otus.spring.hw14librarymvc.services.AuthorService;
import ru.geracimov.otus.spring.hw14librarymvc.services.BookService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @Test
    public void showAuthorBookTest() throws Exception {
        UUID id = UUID.randomUUID();
        Author a = new Author("authorname", LocalDate.now());
        a.setId(id);
        given(authorService.getAuthorById(id)).willReturn(Optional.of(a));
        mvc.perform(get("/author/" + id.toString() + "/book"))
           .andExpect(status().isOk())
           .andExpect(view().name("author-book"))
           .andExpect(model().hasNoErrors())
           .andExpect(content().string(Matchers.allOf(
                   Matchers.containsString(a.getName()),
                   Matchers.containsString(a.getBirth()
                                            .toString())

           )));
    }

    @Test
    void addAuthorTest() throws Exception {
        LocalDate localDate = LocalDate.now();
        Author a = new Author("authorname", localDate);
        mvc.perform(get("/author/add"))
           .andExpect(status().isOk())
           .andExpect(view().name("author-add"));

        mvc.perform(
                post("/author/add")
                        .param("name", a.getName())
                        .param("birth",
                               a.getBirth()
                                .toString()))
           .andExpect(model().hasNoErrors())
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/author"));
    }

    @Test
    void checkEmptyAuthorNameTest() throws Exception {
        mvc.perform(
                post("/author/add")
                        .param("name", "")
                        .param("birth",
                               LocalDate.now()
                                        .toString()))
           .andExpect(view().name("author-add"))
           .andExpect(model().hasErrors());
    }
}