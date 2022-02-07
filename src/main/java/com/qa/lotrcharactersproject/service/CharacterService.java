package com.qa.lotrcharactersproject.service;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import com.qa.lotrcharactersproject.repo.CharacterRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService implements CRUDCharacterInterface<LOTRCharacter>{

    private CharacterRepo repo;

    public CharacterService(CharacterRepo repo) {
        this.repo = repo;
    }

    @Override
    public LOTRCharacter create(LOTRCharacter character) {
        return this.repo.save(character);
    }

    @Override
    public List<LOTRCharacter> readAll() {
        return this.repo.findAll();
    }

    @Override
    public LOTRCharacter readById(Long id) {

    }

    @Override
    public LOTRCharacter update(LOTRCharacter character, Long id) {
        return null;
    }

    @Override
    public boolean delete(LOTRCharacter character) {
        return false;
    }
}
