package com.qa.lotrcharactersproject.service;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import com.qa.lotrcharactersproject.repo.CharacterRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<LOTRCharacter> optChar = this.repo.findById(id);
        return optChar.get();

    }

    @Override
    public LOTRCharacter update(LOTRCharacter character, Long id) {
        Optional<LOTRCharacter> optChar = this.repo.findById(id);
        if (optChar.isPresent()){
            optChar.get().setName(character.getName());
            optChar.get().setAge(character.getAge());
            optChar.get().setRace(character.getRace());
            return this.repo.save(optChar.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<LOTRCharacter> optChar = this.repo.findById(id);
        if (optChar.isPresent()) {
            this.repo.deleteById(id);
            return !this.repo.existsById(id);
        }
        return this.repo.existsById(id);
    }
}
