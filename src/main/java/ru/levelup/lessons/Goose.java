package ru.levelup.lessons;

public class Goose {
    public String sound;
    public int size;

    public Goose() {
        this.sound = "Ga-ga";
        this.size = 16;
    }

    @Override
    public String toString() {
        return "Goose{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                '}';
    }
}
