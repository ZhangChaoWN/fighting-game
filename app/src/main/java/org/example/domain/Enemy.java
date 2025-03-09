package org.example.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Enemy {

    private Integer locationX;

    private Integer locationY;
}
