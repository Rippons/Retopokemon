package org.pokemones;

import lombok.Getter;
import lombok.Setter;

public class Type {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }


}


