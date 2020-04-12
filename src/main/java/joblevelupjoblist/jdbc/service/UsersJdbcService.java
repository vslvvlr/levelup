package joblevelupjoblist.jdbc.service;

import joblevelupjoblist.domain.User;
import joblevelupjoblist.jdbc.jdbcUtils;
import java.sql.*;

public class UsersJdbcService implements UsersService {
    @Override
    public User createUser(String name, String lastName, String passport) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into users  (name, last_name, passport) values (?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setString(3, passport);

        int rowChange = statement.executeUpdate();
        System.out.println("Количество строк:" + rowChange);

        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select * from users where name = '" + name + "'" + " and last_name = '" + lastName + "'" + " and passport = '" + passport + "'");

        return extractPosition(resultSet);
    }

    @Override
    public User findByPassport(String passport) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where passport = ?");
            statement.setString(1, passport);
            ResultSet resultSet = statement.executeQuery();

            return extractPosition(resultSet);
        }
    }

    @Override
    public User findByNameAndLastName(String name, String lastName) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where name = ? and last_name = ?");
            statement.setString(1, name);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();

            return extractPosition(resultSet);
        }
    }

    @Override
    public User findByLastName(String lastName) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where last_name = ?");
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();

            return extractPosition(resultSet);
        }
    }

    @Override
    public void deleteUserByPassport(String passport) throws SQLException {
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where passport = ?");
            statement.setString(1, passport);

            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }

    }

    @Override
    public User updateUser(String passport, String name, String lastName) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set name = ?, last_name = ? where passport = ?");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, passport);

            int rowChange = statement.executeUpdate();
            System.out.println("Количество строк:" + rowChange);

            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from users where name = '" + name + "'" + " and last_name = '" + lastName + "'" + " and passport = '" + passport + "'");

            return extractPosition(resultSet);
        }
    }

    private User extractPosition(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String passport = resultSet.getString("passport");

        return new User(id,name,lastName, passport);
    }
}
