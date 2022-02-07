package com.qa.lotrcharactersproject.repo;

import com.qa.lotrcharactersproject.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepo extends JpaRepository<Character, Long> {
}
