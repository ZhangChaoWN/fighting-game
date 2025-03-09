package org.example.repository.impl;

import org.example.domain.GameMap;
import org.example.repository.GameMapRepository;

public class GameMapRepositoryImpl implements GameMapRepository {

    private GameMap currentMap;

    @Override
    public void saveCurrentMap(GameMap map) {
        currentMap = map;
    }

    @Override
    public GameMap queryCurrentMap() {
        return currentMap;
    }
}
