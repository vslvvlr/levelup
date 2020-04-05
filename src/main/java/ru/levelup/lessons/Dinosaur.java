package ru.levelup.lessons;

public class Dinosaur {
    public String sound;
    public int size;

    public Dinosaur() {
        this.sound = "ROAAAAAARRRR";
        this.size = 100;
    }

    @Override
    public String toString() {
        return "Dinosaur{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                '}';
    }
}
