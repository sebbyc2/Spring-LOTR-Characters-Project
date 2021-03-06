package com.qa.lotrcharactersproject.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LOTRCharacterTest {

    @Test
    public void testConstructorWithId(){
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
    public void testConstructorWithoutId(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        assertNotNull(character.getName());
        assertNotNull(character.getAge());
        assertNotNull(character.getRace());
        assertEquals("Aragorn", character.getName());
        assertEquals(87, character.getAge());
        assertEquals("Human", character.getRace());
    }

    @Test
    public void testEmptyConstructor(){
        LOTRCharacter character = new LOTRCharacter();
        assertNull(character.getId());
        assertNull(character.getName());
        assertNull(character.getAge());
        assertNull(character.getRace());
    }

    @Test
    public void testSetId(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        character.setId(5L);

        assertEquals(5L, character.getId());
    }

    @Test
    public void testSetName(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        character.setName("Arwen");

        assertEquals("Arwen", character.getName());
    }

    @Test
    public void testSetAge(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        character.setAge(93);

        assertEquals(93, character.getAge());
    }

    @Test
    public void testSetRace(){
        LOTRCharacter character = new LOTRCharacter("Aragorn", 87, "Human");
        character.setRace("Half-Elf");

        assertEquals("Half-Elf", character.getRace());
    }

    @Test
    public void hashCodeAndEqualsTest() {
        EqualsVerifier.simple().forClass(LOTRCharacter.class).verify();
    }

    @Test
    public void testToString(){
        LOTRCharacter character = new LOTRCharacter(3L,"Aragorn", 87, "Human");
        assertEquals("LOTRCharacter{id=3, name='Aragorn', age=87, race='Human'}", character.toString());
    }
}
