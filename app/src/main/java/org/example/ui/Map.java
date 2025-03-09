package org.example.ui;

import static org.example.constants.MapConfig.*;

import org.example.domain.GameMap;

public class Map {

    private static final int WIDTH = 61;
    private static final int HEIGHT = 21;
    private static final char CHARACTER_SYMBOL = '#';
    private static final char ENEMY_SYMBOL = '*';
    private static final char BORDER_SYMBOL = '.';
    private static final char EMPTY_SYMBOL = ' ';
    private static final char LINE_FEED = '\n';

    public String render(GameMap gameMap) {

        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;
        int startX = gameMap.getCharacter().getLocationX() - centerX;
        int startY = gameMap.getCharacter().getLocationY() - centerY;

        char[][] points = new char[HEIGHT][WIDTH];

        fillBorderAndEmptySpace(startX, startY, points);
        fillCharacterAndEnemies(gameMap, points, centerX, centerY, startX, startY);

        return renderString(points);
    }

    private static String renderString(char[][] points) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                builder.append(points[i][j]);
            }
            builder.append(LINE_FEED);
        }
        return builder.toString();
    }

    private static void fillCharacterAndEnemies(
            GameMap gameMap, char[][] points, int centerX, int centerY, int startX, int startY) {
        gameMap.getEnemies().forEach(e -> {
            if (e.getLocationX() - startX >= 0
                    && e.getLocationX() - startX < WIDTH
                    && e.getLocationY() - startY >= 0
                    && e.getLocationY() - startY < HEIGHT) {
                points[e.getLocationY() - startY][e.getLocationX() - startX] = ENEMY_SYMBOL;
            }
        });

        points[centerY][centerX] = CHARACTER_SYMBOL;
    }

    private static void fillBorderAndEmptySpace(int startX, int startY, char[][] points) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (startX + j < LOCATION_X_MIN
                        || startX + j > LOCATION_X_MAX
                        || startY + i < LOCATION_Y_MIN
                        || startY + i > LOCATION_Y_MAX) {
                    points[i][j] = BORDER_SYMBOL;
                } else {
                    points[i][j] = EMPTY_SYMBOL;
                }
            }
        }
    }
}
