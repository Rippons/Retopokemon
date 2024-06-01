package org.pokemones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/pokemon_db";
    private static final String USER = "root"; //
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el controlador JDBC", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



