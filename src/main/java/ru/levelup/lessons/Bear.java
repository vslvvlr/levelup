package ru.levelup.lessons;

public class Bear {
    public String sound;
    public int size;

    public Bear() {
        this.sound = "Roaaaarrr";
        this.size = 26;
    }

    @Override
    public String toString() {
        return "Bear{" +
                "sound='" + sound + '\'' +
                ", size=" + size +
                '}';
    }
}
