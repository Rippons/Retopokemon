package org.pokemones;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Trainer {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int age;
    @Getter @Setter
    private String region;

    public Trainer(int id, String name, int age, String region) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.region = region;
    }

    public Trainer(int id, String name, int age, String region, ArrayList<Object> objects) {
    }



    // Getters y setters
    // ...
}
