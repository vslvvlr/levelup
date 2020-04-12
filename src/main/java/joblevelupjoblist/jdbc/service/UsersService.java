package joblevelupjoblist.jdbc.service;

import joblevelupjoblist.domain.User;

import java.sql.SQLException;

public interface UsersService {

    User createUser(String name, String lastName, String passport) throws SQLException;
    User findByPassport(String passport) throws SQLException;
    User findByNameAndLastName(String name, String lastName) throws SQLException;
    User findByLastName(String lastName) throws SQLException;
    void deleteUserByPassport(String passport) throws SQLException;
    User updateUser(String passport, String name, String lastName) throws SQLException;
}
