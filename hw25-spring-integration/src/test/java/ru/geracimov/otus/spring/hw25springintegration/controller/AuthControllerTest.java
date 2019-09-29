package ru.geracimov.otus.spring.hw25springintegration.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.geracimov.otus.spring.hw25springintegration.config.security.CustomAuthenticationProvider;
import ru.geracimov.otus.spring.hw25springintegration.integration.AuthorGateway;
import ru.geracimov.otus.spring.hw25springintegration.services.AuthorService;
import ru.geracimov.otus.spring.hw25springintegration.services.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorController.class)
@DisplayName("Запрос авторов")
@Import({CustomAuthenticationProvider.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AuthorService authorService;
    @MockBean
    BookService bookService;
    @MockBean
    UserDetailsService userDetailsService;
    @MockBean
    AuthorGateway authorGateway;

    @Test
    @DisplayName("без аутентификации завершается с редиректом на логин")
    public void testAuthenticatedWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/author"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("http://localhost/login"))
        ;
    }

    @Test
    @DisplayName("с аутентификацией завершается успешно")
    @WithMockUser(username = "USER", authorities = {"USER_ROLE"})
    public void testAuthenticatedWithAuthentication() throws Exception {
        mockMvc.perform(get("/author"))
               .andExpect(status().isOk())
        ;
    }

}
