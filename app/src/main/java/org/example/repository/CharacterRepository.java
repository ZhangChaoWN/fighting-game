package org.example.repository;

import org.example.domain.Character;

public interface CharacterRepository {

    void saveCurrentCharacter(Character character);

    Character queryCurrentCharacter();
}
