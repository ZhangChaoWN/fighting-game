package org.example.ui;

import org.example.domain.Character;
import org.example.domain.GameMap;

public class MainWindow {

    private static final String RENDER_TEMPLATE =
            "Player: %s | Exp: %s\nMove: W, A, S, D | Fight: F | Send command: Enter\n";

    private Map map = new Map();

    public String render(GameMap gameMap) {
        Character character = gameMap.getCharacter();
        return String.format(RENDER_TEMPLATE, character.getName(), character.getExperience()) + map.render(gameMap);
    }
}
