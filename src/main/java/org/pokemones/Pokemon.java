package org.pokemones;

import lombok.Getter;
import lombok.Setter;

public class Pokemon {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String image;
    @Getter @Setter
    private int trainerId;

    public Pokemon(int id, String name, String image, int trainerId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.trainerId = trainerId;
    }



    // Getters y setters
    // ...
}


