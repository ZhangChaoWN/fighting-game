package org.example.ui;

import org.example.domain.Character;

public class MainWindow {

    private static final String RENDER_TEMPLATE = "Player: %s";

    public String render(Character character) {
        return String.format(RENDER_TEMPLATE, character.getName());
    }
}
