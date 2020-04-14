package ru.levelup.lessons;

import javax.persistence.Entity;

@Entity
public class Goose {
    private String sound;
    private int size;
    @RandomInt
    private int amount;

    public Goose() {
        this.sound = "Ga-ga";
        this.size = 16;
    }

    @Override
    public String toString() {
        return "Goose{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                ", amount=" + amount +
                '}';
    }
}
