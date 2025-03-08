package org.example.service.impl;

import org.example.domain.Character;
import org.example.repository.CharacterRepository;
import org.example.repository.impl.CharacterRepositoryImpl;
import org.example.service.GameService;

public class GameServiceImpl implements GameService {

    private CharacterRepository characterRepository = new CharacterRepositoryImpl();

    @Override
    public Character createCharacter(String characterName) {
        Character character = Character.builder().name(characterName).build();
        characterRepository.saveCurrentCharacter(character);
        return character;
    }

    @Override
    public Character queryCurrentCharacter() {
        return characterRepository.queryCurrentCharacter();
    }
}
