package joblevelupjoblist.jdbc.service;

import joblevelupjoblist.domain.Position;
import joblevelupjoblist.jdbc.jdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class jdbcJobListService {
    public Position createPosition(String name) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        // insert~select~update~delete
        // Statement
        // PreparedStatement удобно можно собирать строки через setstring, можно не переписывать код под разные базы данных
        // CallableStatement вызывать процедуру которая в базе описана

        PreparedStatement statement = connection.prepareStatement("insert into positions (name) values (?)");
        statement.setString(1, name);

        int rowChange = statement.executeUpdate(); // insert/delete/update
        System.out.println("Количество строк:" + rowChange);

        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select * from positions where name = '" + name + "'"); //select

        resultSet.next();
        int id = resultSet.getInt(1);
        String positionName = resultSet.getString("name");
        System.out.println("Должность: ID=" + id + ", name=" + positionName);

        return new Position(id,positionName);
        //ORM - object relation mapping
    }

    public void deletePosition(String name) throws SQLException {
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from positions where name = ?");
            statement.setString(1, name);

            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }

        //connection.close()
    }

    public Collection<Position> findAll() throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from positions");

            return extractPositions(resultSet);
        }
    }

    public Collection<Position> findPositionWithNameLike (String name) throws SQLException {
        // like
        // "Developer" -> "Developer"
        // "%Developer" -> "Java Developer"
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where name like ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            return extractPositions(resultSet);
        }
    }

    private Collection<Position> extractPositions(ResultSet resultSet) throws SQLException {
        Collection<Position> positions = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            positions.add(new Position(id, name));
        }

        return positions;
    }
}
