package ru.geracimov.otus.spring.hw15libraryreact.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;
import ru.geracimov.otus.spring.hw15libraryreact.repository.AuthorRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class AuthorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .build();
        author1 = new Author("name", LocalDate.of(2019, 1, 1));
        authorRepository.save(author1);
    }

    @Test
    void getAuthors() throws Exception {
        mockMvc.perform(get("/api/author").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().string(Matchers.allOf(
                       Matchers.containsString(author1.getName()),
                       Matchers.containsString(author1.getBirth()
                                                      .toString()))));
    }

    @Test
    void createAuthor() throws Exception {
        String author1String = objectMapper.writeValueAsString(author1);


        MvcResult result = this.mockMvc.perform(
                post("/api/author")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(author1String)
        )
                                       .andExpect(status().is(200))
                                       .andExpect(content().string(Matchers.allOf(
                                               Matchers.containsString(author1.getName()),
                                               Matchers.containsString(author1.getBirth()
                                                                              .toString())
                                       )))
                                       .andReturn();

    }


    @Test
    void deleteAuthor() throws Exception {
        String authorName = "Антон Чехов";
        Author author = authorRepository.findAuthorByName(authorName);
        assertThat(author).isNotNull()
                          .hasFieldOrPropertyWithValue("name", authorName);

        this.mockMvc.perform(delete("/api/author/" + author.getId()))
                    .andExpect(status().is(200));
    }
}