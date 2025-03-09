package org.example.service;

import org.example.domain.Character;
import org.example.domain.GameMap;

public interface GameService {

    Character createCharacter(String name);

    Character queryCurrentCharacter();

    void move(Character character, int deltaX, int deltaY);

    GameMap initMap();
}
