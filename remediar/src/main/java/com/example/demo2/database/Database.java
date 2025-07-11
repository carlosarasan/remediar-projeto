package com.example.demo2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/remediar?useUnicode=true&characterEncoding=UTF-8";

    private static final String USER = "root"; //
    private static final String PASSWORD = null;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

