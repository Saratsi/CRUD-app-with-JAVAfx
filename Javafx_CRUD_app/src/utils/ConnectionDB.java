package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3308/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        try
        {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}