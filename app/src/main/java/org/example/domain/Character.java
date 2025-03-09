package org.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Character {

    private String name;

    private Integer locationX;

    private Integer locationY;
}
