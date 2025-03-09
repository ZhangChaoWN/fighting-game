package org.example.service.impl;

import static org.example.constants.MapConfig.*;

import java.util.*;
import java.util.stream.Collectors;
import org.example.domain.Character;
import org.example.domain.Enemy;
import org.example.domain.GameMap;
import org.example.repository.CharacterRepository;
import org.example.repository.GameMapRepository;
import org.example.repository.impl.CharacterRepositoryImpl;
import org.example.repository.impl.GameMapRepositoryImpl;
import org.example.service.GameService;

public class GameServiceImpl implements GameService {

    private static final Integer BONUS_EXP_MAX = 100;
    private static final int MAX_ENEMIES_ON_MAP = 80;

    private CharacterRepository characterRepository = new CharacterRepositoryImpl();

    private GameMapRepository gameMapRepository = new GameMapRepositoryImpl();

    private Random random = new Random();

    @Override
    public Character createCharacter(String characterName) {
        Character character = Character.builder()
                .name(characterName)
                .locationX(0)
                .locationY(0)
                .experience(0)
                .build();
        characterRepository.saveCurrentCharacter(character);
        return character;
    }

    @Override
    public Character queryCurrentCharacter() {
        return characterRepository.queryCurrentCharacter();
    }

    @Override
    public void move(Character character, int deltaX, int deltaY) {
        if (deltaX > 0 && (long) character.getLocationX() + deltaX <= LOCATION_X_MAX
                || deltaX < 0 && (long) character.getLocationX() + deltaX > LOCATION_X_MIN) {
            character.setLocationX(character.getLocationX() + deltaX);
        }
        if (deltaY > 0 && (long) character.getLocationY() + deltaY <= LOCATION_Y_MAX
                || deltaY < 0 && (long) character.getLocationY() + deltaY > LOCATION_Y_MIN) {
            character.setLocationY(character.getLocationY() + deltaY);
        }
        characterRepository.saveCurrentCharacter(character);
    }

    @Override
    public GameMap initMap() {
        Set<List<Integer>> points = new HashSet<>();
        while (points.size() < MAX_ENEMIES_ON_MAP) {
            int x = random.nextInt(LOCATION_X_MAX - LOCATION_X_MIN + 1);
            int y = random.nextInt(LOCATION_Y_MAX - LOCATION_Y_MIN + 1);
            points.add(Arrays.asList(x, y));
        }

        GameMap gameMap = GameMap.builder()
                .character(queryCurrentCharacter())
                .enemies(new ArrayList<>(points.stream()
                        .map(p -> Enemy.builder()
                                .locationX(p.get(0))
                                .locationY(p.get(1))
                                .bonusExp(random.nextInt(BONUS_EXP_MAX))
                                .build())
                        .collect(Collectors.toList())))
                .build();
        gameMapRepository.saveCurrentMap(gameMap);
        return gameMap;
    }

    @Override
    public void attack(GameMap gameMap) {
        Iterator<Enemy> iterator = gameMap.getEnemies().iterator();
        Character character = gameMap.getCharacter();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (Math.abs(enemy.getLocationX() - character.getLocationX()) == 1
                            && enemy.getLocationY().equals(character.getLocationY())
                    || Math.abs(enemy.getLocationY() - character.getLocationY()) == 1
                            && enemy.getLocationX().equals(character.getLocationX())) {
                character.setExperience(character.getExperience() + enemy.getBonusExp());
                iterator.remove();
                return;
            }
        }
    }
}
