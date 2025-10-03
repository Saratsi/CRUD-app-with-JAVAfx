package utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateUser {

    public static boolean updateUser(int id,String name,String email)
    {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id=?";
        try(Connection conn = ConnectionDB.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,email);
            stmt.setInt(3,id);
            return stmt.executeUpdate() > 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}