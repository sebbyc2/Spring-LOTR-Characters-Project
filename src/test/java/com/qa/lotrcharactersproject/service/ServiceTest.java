package com.qa.lotrcharactersproject.service;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import com.qa.lotrcharactersproject.repo.CharacterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //create test
    @Test
    public void createTest(){
        Mockito.when(this.repo.save(newCharacter)).thenReturn(savedCharacter);
        assertEquals(this.service.create(newCharacter), savedCharacter);
        Mockito.verify(this.repo, Mockito.times(1)).save(newCharacter);
    }

    // Read all test
    @Test
    public void readAllTest(){
        List<LOTRCharacter> mockList = new ArrayList<>();

        LOTRCharacter char1 = new LOTRCharacter(2L, "Aragorn", 87, "Human");
        LOTRCharacter char2 = new LOTRCharacter(3L, "Legolas", 2931, "Human");

        mockList.add(char1);
        mockList.add(char2);

        Mockito.when(this.repo.findAll()).thenReturn(mockList);
        assertEquals(this.service.readAll(), mockList);
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    //Read By ID tests
    @Test
    public void readByIdTestWithValidId(){
        this.repo.save(newCharacter);
        Long validId = 1L;
        Optional<LOTRCharacter> optChar = Optional.ofNullable(savedCharacter);
        Mockito.when(this.repo.findById(validId)).thenReturn(optChar);
        assertEquals(this.service.readById(validId), savedCharacter);
        Mockito.verify(this.repo, Mockito.times(1)).save(newCharacter);
        Mockito.verify(this.repo, Mockito.times(1)).findById(validId);
    }

    @Test
    public void readByIdTestWithoutInvalidId() {
        Long invalidId = 2L;
        Mockito.when(this.repo.findById(invalidId)).thenReturn(Optional.ofNullable(null));
        assertEquals(this.service.readById(invalidId), null);
        Mockito.verify(this.repo, Mockito.times(1)).findById(invalidId);
    }

    //Update tests
    @Test
    public void testUpdateWithValidId(){
        Long validId = 1L;
        Optional<LOTRCharacter> optChar = Optional.ofNullable(savedCharacter);

        LOTRCharacter updatedChar = new LOTRCharacter("Aragorn", 87, "Human");
        LOTRCharacter savedUpdatedChar = new LOTRCharacter(1L,"Aragorn", 87, "Human");

        Mockito.when(this.repo.findById(validId)).thenReturn(optChar);
        Mockito.when(this.repo.save(savedUpdatedChar)).thenReturn(savedUpdatedChar);
        assertEquals(this.service.update(validId, updatedChar), savedUpdatedChar);
        Mockito.verify(this.repo, Mockito.times(1)).save(savedUpdatedChar);
        Mockito.verify(this.repo, Mockito.times(1)).findById(validId);
    }

    @Test void testUpdateWithInvalidId(){
        Long invalidId = 3L;
        LOTRCharacter updatedChar = new LOTRCharacter("Aragorn", 87, "Human");
        Mockito.when(this.repo.findById(invalidId)).thenReturn(Optional.ofNullable(null));
        assertEquals(this.service.update(invalidId, updatedChar), null);
        Mockito.verify(this.repo, Mockito.times(1)).findById(invalidId);
    }

    //Delete tests

    @Test
    public void testDeleteWithValidId(){

        Long validId = 1L;
        Optional<LOTRCharacter> optChar = Optional.ofNullable(savedCharacter);
        Mockito.when(this.repo.findById(validId)).thenReturn(optChar);
        assertEquals(this.service.delete(validId), true);
        Mockito.verify(this.repo, Mockito.times(1)).findById(validId);
        Mockito.verify(this.repo, Mockito.times(1)).deleteById(validId);
    }

    @Test
    public void testDeleteWithInvalidId(){
        Long invalidId = 2L;
        Mockito.when(this.repo.existsById(invalidId)).thenReturn(false);
        assertEquals(this.service.delete(invalidId), false);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(invalidId);
    }
}
