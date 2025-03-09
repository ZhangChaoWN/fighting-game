package org.example;

import org.example.ui.WelcomePrompt;

import java.io.IOException;

public class App {

    private static final Character QUIT_COMMAND = 'q';

    private static final Character LINE_FEED = '\n';

    private static final String CLEAN_SCREEN_ESCAPE = "\033[H\033[2J";

    public static void main(String[] args) throws IOException {

        clearScreen();
        WelcomePrompt welcomePrompt = new WelcomePrompt();
        System.out.println(welcomePrompt.render());

        while (true) {
            int key = System.in.read();
            if (key == LINE_FEED) {
                continue;
            }
            clearScreen();
            if (key == QUIT_COMMAND) {
                break;
            }
        }
    }

    private static void clearScreen() {
        System.out.print(CLEAN_SCREEN_ESCAPE);
        System.out.flush();
    }
}
