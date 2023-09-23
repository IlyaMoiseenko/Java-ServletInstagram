package by.moiseenko.instagram.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    @author Ilya Moiseenko on 23.09.23
*/
public class JdbcConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/InstagramClone";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
