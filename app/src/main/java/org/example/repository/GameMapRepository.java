package org.example.repository;

import java.io.IOException;
import org.example.domain.GameMap;

public interface GameMapRepository {

    void saveCurrentMap(GameMap map) throws IOException;

    GameMap queryCurrentMap();

    GameMap loadSavedMap() throws IOException;

    void setSavePath(String path);
}
