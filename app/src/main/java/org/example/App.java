package org.example;

import java.io.IOException;
import java.util.Scanner;
import org.example.domain.GameMap;
import org.example.service.GameService;
import org.example.service.impl.GameServiceImpl;
import org.example.ui.MainWindow;
import org.example.ui.WelcomePrompt;

public class App {
    private static final char MOVE_LEFT = 'a';
    private static final char MOVE_RIGHT = 'd';
    private static final char MOVE_UP = 'w';
    private static final char MOVE_DOWN = 's';
    private static final char ATTACK = 'f';
    private static final Character QUIT_COMMAND = 'q';
    private static final int EOF = -1;
    private static final String CLEAN_SCREEN_ESCAPE = "\033[H\033[2J";

    public static void main(String[] args) throws IOException {
        MainWindow mainWindow = new MainWindow();
        Scanner scanner = new Scanner(System.in);
        String savePath = parseSavePath(args);
        GameService gameService = new GameServiceImpl(savePath);

        try (scanner) {
            GameMap gameMap = gameService.loadMap();
            if (gameMap == null) {
                createCharacter(scanner, gameService);
                gameMap = gameService.initMap();
            }

            while (true) {
                clearScreen();
                output(mainWindow.render(gameMap));
                int key = System.in.read();
                if (key == QUIT_COMMAND || key == EOF) {
                    gameService.saveGame();
                    break;
                }
                char lowerKey = Character.toLowerCase((char) key);
                org.example.domain.Character character = gameMap.getCharacter();
                switch (lowerKey) {
                    case MOVE_LEFT -> gameService.move(character, -1, 0);
                    case MOVE_RIGHT -> gameService.move(character, 1, 0);
                    case MOVE_UP -> gameService.move(character, 0, -1);
                    case MOVE_DOWN -> gameService.move(character, 0, 1);
                    case ATTACK -> gameService.attack(gameMap);
                }
            }
        }
    }

    private static void clearScreen() {
        System.out.print(CLEAN_SCREEN_ESCAPE);
        System.out.flush();
    }

    private static void output(String content) {
        System.out.println(content);
    }

    private static org.example.domain.Character createCharacter(Scanner scanner, GameService gameService) {
        clearScreen();
        WelcomePrompt welcomePrompt = new WelcomePrompt();
        output(welcomePrompt.render());

        String name = scanner.nextLine();
        return gameService.createCharacter(name);
    }

    private static String parseSavePath(String[] args) {
        if (args.length > 0) {
            return args[0];
        } else {
            return null;
        }
    }
}
