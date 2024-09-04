package com.taskbot.vs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/vsrunner";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "djdfy12345";

    private static volatile Connection connection;


    private DatabaseConnection() {}

    public static Connection getInstance() throws SQLException {
        if(connection == null) {

            synchronized (DatabaseConnection.class) {

                if(connection == null) {
                    connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                    logger.info("Database connection established.");
                }

            }

        }

        return connection;
    }
}
