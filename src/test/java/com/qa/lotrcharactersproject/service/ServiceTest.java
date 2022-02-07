package com.qa.lotrcharactersproject.service;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import com.qa.lotrcharactersproject.repo.CharacterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTest {

    private LOTRCharacter newCharacter;
    private LOTRCharacter savedCharacter;

    @Autowired
    private CharacterService service;

    @MockBean
    private CharacterRepo repo;

    @BeforeEach
    public void setUp() {
        newCharacter = new LOTRCharacter("Gandalf", 24000, "Maia");
        savedCharacter = new LOTRCharacter(1L,"Gandalf", 24000, "Maia");
    }

    @Test
    public void createTest(){
        Mockito.when(this.repo.save(newCharacter)).thenReturn(savedCharacter);
        assertEquals(this.service.create(newCharacter), savedCharacter);
        Mockito.verify(this.repo, Mockito.times(1)).save(newCharacter);
    }



}
