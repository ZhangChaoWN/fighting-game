package org.example.service.impl;

import org.example.domain.Character;
import org.example.repository.CharacterRepository;
import org.example.repository.impl.CharacterRepositoryImpl;
import org.example.service.GameService;

public class GameServiceImpl implements GameService {

    private static final Integer LOCATION_X_MAX = Integer.MAX_VALUE;
    private static final Integer LOCATION_X_MIN = Integer.MIN_VALUE;
    private static final Integer LOCATION_Y_MAX = Integer.MAX_VALUE;
    private static final Integer LOCATION_Y_MIN = Integer.MIN_VALUE;

    private CharacterRepository characterRepository = new CharacterRepositoryImpl();

    @Override
    public Character createCharacter(String characterName) {
        Character character = Character.builder()
                .name(characterName)
                .locationX(0)
                .locationY(0)
                .build();
        characterRepository.saveCurrentCharacter(character);
        return character;
    }

    @Override
    public Character queryCurrentCharacter() {
        return characterRepository.queryCurrentCharacter();
    }

    @Override
    public void move(Character character, int deltaX, int deltaY) {
        if (deltaX > 0 && (long) character.getLocationX() + deltaX <= LOCATION_X_MAX
                || deltaX < 0 && (long) character.getLocationX() + deltaX > LOCATION_X_MIN) {
            character.setLocationX(character.getLocationX() + deltaX);
        }
        if (deltaY > 0 && (long) character.getLocationY() + deltaY <= LOCATION_Y_MAX
                || deltaY < 0 && (long) character.getLocationY() + deltaY > LOCATION_Y_MIN) {
            character.setLocationY(character.getLocationY() + deltaY);
        }
        characterRepository.saveCurrentCharacter(character);
    }
}
