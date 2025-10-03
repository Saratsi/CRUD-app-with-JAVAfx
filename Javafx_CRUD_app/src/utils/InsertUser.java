package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertUser {

    public static boolean addUser(String name,String email)
    {
        String sql = "INSERT INTO users (name,email) VALUES (?,?)";
        try(Connection conn = ConnectionDB.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,email);
            return stmt.executeUpdate() > 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
