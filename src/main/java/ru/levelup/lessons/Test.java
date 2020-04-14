package ru.levelup.lessons;

import javax.persistence.Entity;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IllegalAnnotationException {
        String packageName = "ru.levelup.lessons";
        List<Class> foundClasses = classFinder(packageName);

        for (int i = 0; i < foundClasses.size(); i++) {
            if (foundClasses.get(i).isAnnotationPresent(Entity.class)) {
                Object object = (foundClasses.get(i).getDeclaredConstructor().newInstance());
                RandomIntAnnotationProcessor.process(object);
                System.out.println(object);
            }
            else
                i++;
        }

    }

    public static List<Class> classFinder(String packageName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        //получение адреса пакета в корректном формате
        String updatedPackageName = packageName.replace(".", "\\");
        String address = "src\\main\\java\\";
        String fullPackageName = address.concat(updatedPackageName);

        //сканирование директории
        File fileDirectory = new File(fullPackageName);
        File[] files = fileDirectory.listFiles();

        // добавление объектов класса Class в коллекцию classes
        List<Class> classes = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String dot = ".";
            String packageNameWithDot = packageName.concat(dot); // получение строки вида "ru.levelup.lessons."
            String classNameWithDotJava = files[i].getName(); // получение имени класса вида "Bear.java"
            classes.add(Class.forName(packageNameWithDot.concat(removeDotJavaFromClassName(classNameWithDotJava))));
        }

        return classes;
    }

    //убрать ".java" из имени класса вида "Bear.java"
    public static String removeDotJavaFromClassName(String classNameWithDotJava) {
        String dotJava = ".java";
        StringBuffer stringBuffer = new StringBuffer(classNameWithDotJava);
        stringBuffer.delete(classNameWithDotJava.length() - dotJava.length(), classNameWithDotJava.length());
        String shortName = stringBuffer.toString();
        return shortName;
    }

}
