package joblevelupjoblist.jdbc.application;

import joblevelupjoblist.jdbc.service.UsersJdbcService;

import java.sql.SQLException;

public class UsersApplication {

    public static void main(String[] args) throws SQLException {
        UsersJdbcService service = new UsersJdbcService();
        System.out.println(service.createUser("georg", "harris", "1234567"));
    }

}
