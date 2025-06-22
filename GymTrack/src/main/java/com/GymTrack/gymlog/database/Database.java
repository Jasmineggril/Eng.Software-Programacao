package br.com.gymtrack.gymlog.database;
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:gymlog.db";
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }
}