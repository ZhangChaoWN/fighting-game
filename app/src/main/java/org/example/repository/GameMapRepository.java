package org.example.repository;

import org.example.domain.GameMap;

public interface GameMapRepository {

    void saveCurrentMap(GameMap map);

}
