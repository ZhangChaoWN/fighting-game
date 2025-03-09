package org.example.service;

import java.io.IOException;
import org.example.domain.Character;
import org.example.domain.GameMap;

public interface GameService {

    Character createCharacter(String name);

    Character queryCurrentCharacter();

    void move(Character character, int deltaX, int deltaY);

    GameMap initMap() throws IOException;

    GameMap loadMap() throws IOException;

    GameMap queryCurrentMap();

    void attack(GameMap gameMap);

    void saveGame() throws IOException;
}
