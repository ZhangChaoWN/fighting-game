package org.example;

import static org.example.utils.MapUtil.findSymbolOnMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.example.domain.Character;
import org.example.domain.Enemy;
import org.example.domain.GameMap;
import org.example.ui.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UITest {

    public static final char CHARACTER_SYMBOL = '#';
    public static final char ENEMY_SYMBOL = '*';

    private Map map = new Map();
    private GameMap gameMap = new GameMap();
    private Character character = new Character();

    @BeforeEach
    public void setUp() {
        character.setLocationX(1);
        character.setLocationY(2);
        gameMap.setCharacter(character);
        gameMap.setEnemies(Arrays.asList(
                Enemy.builder().locationX(3).locationY(4).build(),
                Enemy.builder().locationX(10).locationY(11).build()));
    }

    @Test
    public void shouldRenderCharacterOnMap() {
        String rendered = map.render(gameMap);

        int characterLocation[] = findSymbolOnMap(rendered, CHARACTER_SYMBOL, 0);
        assertEquals(1, characterLocation[0]);
        assertEquals(2, characterLocation[1]);

        int enemy1Location[] = findSymbolOnMap(rendered, ENEMY_SYMBOL, 0);
        assertEquals(3, enemy1Location[0]);
        assertEquals(4, enemy1Location[1]);

        int enemy2Location[] = findSymbolOnMap(rendered, ENEMY_SYMBOL, 1);
        assertEquals(10, enemy2Location[0]);
        assertEquals(11, enemy2Location[1]);
    }
}
