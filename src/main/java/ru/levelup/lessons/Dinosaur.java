package ru.levelup.lessons;

import javax.persistence.Entity;

@Entity
public class Dinosaur {
    @RandomInt
    private String sound;
    private int size;
    @RandomInt
    private int amount;

    public Dinosaur() {
        this.sound = "ROAAAAAARRRR";
        this.size = 100;
    }

    @Override
    public String toString() {
        return "Dinosaur{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                ", amount=" + amount +
                '}';
    }
}
