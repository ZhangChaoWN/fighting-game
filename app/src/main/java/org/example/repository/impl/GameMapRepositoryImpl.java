package org.example.repository.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.example.domain.Enemy;
import org.example.domain.GameMap;
import org.example.repository.GameMapRepository;

public class GameMapRepositoryImpl implements GameMapRepository {

    public String savePath = "game_data.txt";
    public static final String DELIMITER = ",";
    public static final String LINE_FEED = "\n";
    private GameMap currentMap;

    @Override
    public void saveCurrentMap(GameMap map) throws IOException {
        currentMap = map;

        FileWriter writer = new FileWriter(savePath);
        try (writer) {
            writer.write(currentMap.getCharacter().getName() + LINE_FEED);
            writer.write(String.join(
                            DELIMITER,
                            currentMap.getCharacter().getLocationX().toString(),
                            currentMap.getCharacter().getLocationY().toString(),
                            currentMap.getCharacter().getExperience().toString())
                    + LINE_FEED);
            for (Enemy enemy : map.getEnemies()) {
                writer.write(String.join(
                                DELIMITER,
                                enemy.getLocationX().toString(),
                                enemy.getLocationY().toString(),
                                enemy.getBonusExp().toString())
                        + LINE_FEED);
            }
        }
    }

    @Override
    public GameMap queryCurrentMap() {
        return currentMap;
    }

    @Override
    public GameMap loadSavedMap() throws IOException {
        if (!new File(savePath).exists()) {
            return null;
        }
        GameMap gameMap = new GameMap();
        try (BufferedReader reader = new BufferedReader(new FileReader(savePath))) {
            org.example.domain.Character character = parseCharacter(reader);
            gameMap.setCharacter(character);

            List<Enemy> enemies = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                enemies.add(parseEnemy(line));
            }
            gameMap.setEnemies(enemies);
        }
        currentMap = gameMap;
        return gameMap;
    }

    private org.example.domain.Character parseCharacter(BufferedReader reader) throws IOException {
        String characterName = reader.readLine();
        String[] characterData = reader.readLine().split(DELIMITER);
        return new org.example.domain.Character(
                characterName,
                Integer.valueOf(characterData[0]),
                Integer.valueOf(characterData[1]),
                Integer.valueOf(characterData[2]));
    }

    private Enemy parseEnemy(String content) {
        String[] enemyData = content.split(DELIMITER);
        return new Enemy(
                Integer.parseInt(enemyData[0]), Integer.parseInt(enemyData[1]), Integer.parseInt(enemyData[2]));
    }

    @Override
    public void setSavePath(String path) {
        this.savePath = path;
    }
}
