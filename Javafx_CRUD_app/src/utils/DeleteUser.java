package utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteUser {

    public static boolean deleteUser(int id)
    {
        String sql = "DELETE FROM users WHERE id=?";
        try(Connection conn = ConnectionDB.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
