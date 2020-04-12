package joblevelupjoblist.jdbc.service;

import joblevelupjoblist.domain.Position;
import joblevelupjoblist.jdbc.jdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PositionJdbcService implements PositionService {
    @Override
    public Position createPosition(String name) throws SQLException {
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into positions (name) values (?)");
        statement.setString(1, name);

        int rowChange = statement.executeUpdate();
        System.out.println("Количество строк:" + rowChange);

        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select * from positions where name = '" + name + "'");

        return extractPosition(resultSet);
    }

    @Override
    public void deletePositionById(int id) throws SQLException {
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from positions where id = ?");
            statement.setInt(1, id);

            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }
    }

    @Override
    public void deletePositionByName(String name) throws SQLException {
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from positions where name = ?");
            statement.setString(1, name);

            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }
    }

    @Override
    public Collection<Position> findAllPositionWhichNameLike(String name) throws SQLException {
        try (Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where name like ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            return extractPositions(resultSet);
        }
    }

    @Override
    public Position findPositionById(int id) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            return extractPosition(resultSet);
        }
    }


    @Override
    public Collection<Position> findAllPositions() throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from positions");

            return extractPositions(resultSet);
        }
    }

    @Override
    public Position findPositionByName(String name) throws SQLException {
        try(Connection connection = jdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            return extractPosition(resultSet);
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

    private Position extractPosition(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Position(id,name);
    }
}
