package com.qa.lotrcharactersproject.repo;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends JpaRepository<LOTRCharacter, Long> {
}
