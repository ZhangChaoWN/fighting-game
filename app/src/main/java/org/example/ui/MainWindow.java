package org.example.ui;

import org.example.domain.GameMap;

public class MainWindow {

    private static final String RENDER_TEMPLATE = "Player: %s\n";

    private Map map = new Map();

    public String render(GameMap gameMap) {
        return String.format(RENDER_TEMPLATE, gameMap.getCharacter().getName()) + map.render(gameMap);
    }
}
