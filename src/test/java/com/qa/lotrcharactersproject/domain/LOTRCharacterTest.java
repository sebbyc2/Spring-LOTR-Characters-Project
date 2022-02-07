package com.qa.lotrcharactersproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LOTRCharacterTest {

    @Test
    public void constructorTestWithId(){
        LOTRCharacter character = new LOTRCharacter(1L, "Gandalf", 24000, "Maia");

    }
}
