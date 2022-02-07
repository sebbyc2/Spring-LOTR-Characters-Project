package com.qa.lotrcharactersproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LOTRCharacterTest {

    @Test
    public void constructorTestWithId(){
        LOTRCharacter character = new LOTRCharacter(2L, "Aragorn", 87, "Human");
        assertNotNull(character.getId());
        assertNotNull(character.getName());
        assertNotNull(character.getAge());
        assertNotNull(character.getRace());
        assertEquals(2L, character.getId());
        assertEquals("Aragorn", character.getName());
        assertEquals(87, character.getAge());
        assertEquals("Human", character.getRace());
    }

    @Test
    public void constructorTestWithoutId(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        assertNotNull(character.getName());
        assertNotNull(character.getAge());
        assertNotNull(character.getRace());
        assertEquals("Aragorn", character.getName());
        assertEquals(87, character.getAge());
        assertEquals("Human", character.getRace());
    }
}
