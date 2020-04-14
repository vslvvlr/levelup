package ru.levelup.lessons;

import javax.persistence.Entity;

@Entity
public class Bear {
    private String sound;
    private int size;
    @RandomInt
    private int amount;

    public Bear() {
        this.sound = "Roaaaarrr";
        this.size = 26;
    }

    @Override
    public String toString() {
        return "Bear{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                ", amount=" + amount +
                '}';
    }
}
