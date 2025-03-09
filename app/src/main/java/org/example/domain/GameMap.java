package org.example.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameMap {

    private List<Enemy> enemies;
}
