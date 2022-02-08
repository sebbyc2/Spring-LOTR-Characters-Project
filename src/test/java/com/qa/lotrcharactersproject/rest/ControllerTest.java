package com.qa.lotrcharactersproject.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:LOTRCharacter-schema.sql", "classpath:LOTRCharacter-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ControllerTest{

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper map;

    @Test
    void testControllerCreate() throws Exception {
        LOTRCharacter newChar = new LOTRCharacter("Aragorn", 87, "Human");
        String newCharJSON = this.map.writeValueAsString(newChar);
        RequestBuilder mockRequest = post("/LOTRCharacter").contentType(MediaType.APPLICATION_JSON).content(newCharJSON);

        LOTRCharacter savedChar = new LOTRCharacter(2L, "Aragorn", 87, "Human");
        String savedCharJSON = this.map.writeValueAsString(savedChar);

        ResultMatcher matchStatus = status().isCreated();
        ResultMatcher matchBody = content().json(savedCharJSON);

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
    }



}
