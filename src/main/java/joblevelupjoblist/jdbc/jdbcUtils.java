package joblevelupjoblist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcUtils {
    public static Connection getConnection() throws SQLException {
        //url
        //jdbc:<vendor name>://<host(IP address)>:<port>/<db name>
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/job2",
                    "postgres",
                    "root"
        );
        return connection;
    }
}
