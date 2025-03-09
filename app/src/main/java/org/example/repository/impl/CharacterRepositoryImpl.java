package org.example.repository.impl;

import org.example.domain.Character;
import org.example.repository.CharacterRepository;

public class CharacterRepositoryImpl implements CharacterRepository {

    private Character currentCharacter;

    @Override
    public void saveCurrentCharacter(Character character) {
        currentCharacter = character;
    }

    @Override
    public Character queryCurrentCharacter() {
        return currentCharacter;
    }
}
