package com.qa.lotrcharactersproject.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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

    LOTRCharacter char1;
    String char1JSON;

    LOTRCharacter savedChar1;
    String savedChar1JSON;

    LOTRCharacter char2;
    String char2JSON;

    LOTRCharacter savedChar2;
    String savedChar2JSON;


    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper map;

    @BeforeEach
    void setup() throws Exception {
        char1 = new LOTRCharacter("Gandalf", 24000, "Maia");
        char1JSON = this.map.writeValueAsString(char1);

        savedChar1 = char1 = new LOTRCharacter(1L,"Gandalf", 24000, "Maia");
        savedChar1JSON = this.map.writeValueAsString(savedChar1);

        char2 = new LOTRCharacter("Aragorn", 87, "Human");
        char2JSON = this.map.writeValueAsString(char2);

        savedChar2 = new LOTRCharacter(2L, "Aragorn", 87, "Human");
        savedChar2JSON = this.map.writeValueAsString(savedChar2);
    }


    @Test
    void testControllerCreate() throws Exception {
         this.mock.perform(post("/api/LOTRCharacter").contentType(MediaType.APPLICATION_JSON).content(char2JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(savedChar2JSON));
    }

    @Test
    void testControllerReadAll() throws Exception {
        List<LOTRCharacter> list = new ArrayList<>(List.of(savedChar1));
        String newCharJSON = this.map.writeValueAsString(list);
        this.mock.perform(get("/api/LOTRCharacter"))
                .andExpect(status().isFound())
                .andExpect(content().json(newCharJSON));
    }

    @Test
    void testControllerReadById() throws Exception {
        Long id = 1L;
        this.mock.perform(get("/api/LOTRCharacter/" + id))
                .andExpect(status().isFound())
                .andExpect(content().json(savedChar1JSON));
    }

    @Test
    void testControllerUpdate() throws Exception {
        Long idToUpdate = 1L;
        LOTRCharacter updated = new LOTRCharacter(1L,"Aragorn", 87, "Human");
        String updatedJSON = this.map.writeValueAsString(updated);
        this.mock.perform(put("/api/LOTRCharacter/" + idToUpdate).contentType(MediaType.APPLICATION_JSON).content(char2JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(updatedJSON));
    }

    @Test
    void testControllerDelete() throws Exception {
        Long idToDelete = 1L;
        this.mock.perform(delete("/api/LOTRCharacter/" + idToDelete)).andExpect(status().isOk()).andExpect(content().string("true"));
    }
}
