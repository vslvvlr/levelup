package ru.levelup.lessons;

import java.lang.reflect.Field;

public class RandomIntAnnotationProcessor {
    public static void process(Object object) throws IllegalAccessException, IllegalAnnotationException {
        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            RandomInt annotation = field.getAnnotation(RandomInt.class);

            if (annotation != null) {
                if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    int amount = getRandom(annotation.min(), annotation.max());
                    field.setAccessible(true);
                    field.set(object, amount);
                }
                else throw new IllegalAnnotationException("Annotation @RandomInt can be applied to int or Integer variables only.");

            }
        }
    }

    public static int getRandom(int a, int b) {
        return a + (int) (Math.random() * ((b - a) + 1));
    }

}

class IllegalAnnotationException extends Exception {
    IllegalAnnotationException(String message) {
        super(message);
    }
}
