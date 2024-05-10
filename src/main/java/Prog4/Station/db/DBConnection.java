package Prog4.Station.db;



import Prog4.Station.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = System.getenv("DB_URL");
    private static final String username = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASSWORD");

    public static Connection getConnection(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseConnectionException(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return getConnection(url, username, password);
    }
}
