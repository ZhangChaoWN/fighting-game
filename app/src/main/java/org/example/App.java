package org.example;

import java.io.IOException;
import java.util.Scanner;
import org.example.domain.GameMap;
import org.example.service.GameService;
import org.example.service.impl.GameServiceImpl;
import org.example.ui.MainWindow;
import org.example.ui.WelcomePrompt;

public class App {

    private static final Character QUIT_COMMAND = 'q';
    private static final int EOF = -1;
    private static final String CLEAN_SCREEN_ESCAPE = "\033[H\033[2J";

    public static void main(String[] args) throws IOException {

        GameService gameService = new GameServiceImpl();
        MainWindow mainWindow = new MainWindow();
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            org.example.domain.Character character = createCharacter(scanner, gameService);
            GameMap gameMap = gameService.initMap();
            while (true) {
                clearScreen();
                output(mainWindow.render(gameMap));
                int key = System.in.read();
                if (key == QUIT_COMMAND || key == EOF) {
                    break;
                }
                char lowerKey = Character.toLowerCase((char) key);
                switch (lowerKey) {
                    case 'a' -> gameService.move(character, -1, 0);
                    case 'd' -> gameService.move(character, 1, 0);
                    case 'w' -> gameService.move(character, 0, -1);
                    case 's' -> gameService.move(character, 0, 1);
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
}
