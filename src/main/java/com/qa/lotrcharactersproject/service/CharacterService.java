package com.qa.lotrcharactersproject.service;

import com.qa.lotrcharactersproject.repo.CharacterRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService implements CRUDCharacterInterface<Character>{

    private CharacterRepo repo;

    public CharacterService(CharacterRepo repo) {
        this.repo = repo;
    }

    @Override
    public Character create(Character character) {
        return this.repo.save(character);
    }

    @Override
    public List<Character> readAll() {
        return null;
    }

    @Override
    public Character readById(Long id) {
        return null;
    }

    @Override
    public Character update(Character character, Long id) {
        return null;
    }

    @Override
    public boolean delete(Character character) {
        return false;
    }
}
