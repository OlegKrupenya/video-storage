package com.testdev.videostorage.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author oleh.krupenia.
 */
public class ConnectionManager {

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        return ConnectionHolder.connection;
    }

    private static class ConnectionHolder {
        private static Connection connection = createConnection();

        private static Connection createConnection() {
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost/storage";
            final String USER = "root";
            final String PASS = "root";
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
