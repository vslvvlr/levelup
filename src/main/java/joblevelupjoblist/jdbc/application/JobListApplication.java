package joblevelupjoblist.jdbc.application;

import joblevelupjoblist.jdbc.service.jdbcJobListService;

import java.sql.SQLException;

public class JobListApplication {
    public static void main(String[] args) throws SQLException {
        /*
         ClassLoader (про механизм загрузки классов) - есть 3 типа, но можно писать свои
         проверка байт кода
         BootstrapclassLoader проверяет загружал ли ранее, если нет то пытается загрузить. Не может то вниз передает. java util, java lang, lava sql
         ExtClassLoader проверяет загружал ли ранее, то наверх. Не может то вниз передает. из спец директории JAVA_HOME/ext джарники
         AppClassLoader проверяет загружал ли ранее, то наверх. Не может то вниз передает. Вне кор джавы, библиотеки и тп. classpath
         MyClassLoader загружал ли раньше этот класс. если он не грузил его раньше, то идет к аппкласслоадеру. Не может то вниз передает -> classnotfoundexception or noClassDefError
        */

        /*
        java reflection api
        создается объект класса, инфа о всех переменных методах интефейсах и проч
        по идее создается один раз для каждого класса
        */

        jdbcJobListService service = new jdbcJobListService();
        for (int i = 0; i < 10; i++) {
            service.createPosition("DM" + i);
        }

        System.out.println();

        /*
        Collection<Position> allPositions = service.findAll();
        for (Position position : allPositions) {
            System.out.println(position.getId() + " " + position.getName());
        }

        System.out.println();
        Collection<Position> likePositions = service.findPositionWithNameLike("Dev%");
        for (Position position : likePositions) {
            System.out.println(position.getId() + " " + position.getName());
        }

         */
    }
}
