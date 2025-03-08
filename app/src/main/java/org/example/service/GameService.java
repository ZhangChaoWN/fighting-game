package org.example.service;

import org.example.domain.Character;

public interface GameService {

    Character createCharacter(String name);

    Character queryCurrentCharacter();
}
