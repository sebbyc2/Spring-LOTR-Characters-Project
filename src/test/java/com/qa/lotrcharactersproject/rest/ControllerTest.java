package com.qa.lotrcharactersproject.rest;

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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        RequestBuilder mockRequest = post("/api/LOTRCharacter").contentType(MediaType.APPLICATION_JSON).content(newCharJSON);

        LOTRCharacter savedChar = new LOTRCharacter(2L, "Aragorn", 87, "Human");
        String savedCharJSON = this.map.writeValueAsString(savedChar);

        ResultMatcher matchStatus = status().isCreated();
        ResultMatcher matchBody = content().json(savedCharJSON);

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
    }

    @Test
    void testControllerReadAll() throws Exception {
        LOTRCharacter newChar = new LOTRCharacter(1L, "Gandalf", 24000, "Maia");
        List<LOTRCharacter> list = new ArrayList<>(List.of(newChar));
        String newCharJSON = this.map.writeValueAsString(list);

        RequestBuilder mockRequest = get("/api/LOTRCharacter");

        ResultMatcher matchStatus = status().isFound();
        ResultMatcher matchBody = content().json(newCharJSON);

        this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
    }

    @Test
    void testControllerUpdate() throws Exception {
        Long idToUpdate = 1L;

        LOTRCharacter toUpdate = new LOTRCharacter("Aragorn", 87, "Human");
        String toUpdateJSON = this.map.writeValueAsString(toUpdate);

        RequestBuilder mockRequest = put("/api/LOTRCharacter/" + idToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toUpdateJSON);

        LOTRCharacter updated = new LOTRCharacter(1L,"Aragorn", 87, "Human");
        String updatedJSON = this.map.writeValueAsString(updated);

        ResultMatcher matchStatus = status().isAccepted();
        ResultMatcher matchBody = content().json(updatedJSON);

        this.mock.perform(mockRequest)
                .andExpect(matchStatus)
                .andExpect(matchBody);
    }

    @Test
    void testControllerDelete() throws Exception {
        this.mock.perform(delete("/api/LOTRCharacter/" + 1L)).andExpect(status().isOk()).andExpect(content().string("true"));
    }
}
