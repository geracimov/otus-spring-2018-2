package ru.geracimov.otus.spring.hw22springsecurityacl.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.security.CustomAuthenticationProvider;
import ru.geracimov.otus.spring.hw22springsecurityacl.controller.AuthorController;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.AuthorService;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.BookService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Запрос к контроллеру авторов")
@WebMvcTest(AuthorController.class)
@Import({CustomAuthenticationProvider.class})
public class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;
    @MockBean
    UserDetailsService userDetailsService;

    @Test
    @DisplayName("корректно отображает список книг для созданного автора")
    @WithMockUser(username = "USER", authorities = {"USER_ROLE"})
    public void showAuthorBookTest() throws Exception {
        Long id = 999L;
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
    @DisplayName("для сохранения перенаправляет на корректное представление")
    @WithMockUser(username = "USER", authorities = {"USER_ROLE"})
    void addAuthorViewCorrectTest() throws Exception {
        Author a = new Author("authorname", LocalDate.now());
        mvc.perform(get("/author/add"))
           .andExpect(status().isOk())
           .andExpect(view().name("author-add"));
    }

    @Test
    @DisplayName("для сохранения завершается без ошибок и перенаправляет на список авторов")
    @WithMockUser(username = "USER", authorities = {"USER_ROLE"})
    void addAuthorTest() throws Exception {
        Author a = new Author("authorname", LocalDate.now());
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
    @DisplayName("с пустым имененм автора завершается с ошибкой")
    @WithMockUser(username = "USER", authorities = {"USER_ROLE"})
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