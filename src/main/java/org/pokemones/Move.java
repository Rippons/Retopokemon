package org.pokemones;

import lombok.Getter;
import lombok.Setter;

public class Move {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int damage;
    @Getter @Setter
    private int moveId;

    public Move(int id, String name, int damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.moveId = id;
    }



    // Getters y setters
    // ...
}
