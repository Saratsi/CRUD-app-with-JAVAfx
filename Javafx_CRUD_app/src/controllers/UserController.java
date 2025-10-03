package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.ConnectionDB;
import utils.DeleteUser;
import utils.UpdateUser;
import utils.InsertUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserController  {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextArea outputArea;

    @FXML
    private void handleAddUser()
    {
        String id = idField.getText();
        if(!id.isEmpty())
        {
            outputArea.setText("ID field must be empty when you add a user.\nThe ID is auto incremented.");
            return;
        }
        String name = nameField.getText();
        String email = emailField.getText();
        if(name.isEmpty() || email.isEmpty())
        {
            outputArea.setText("Name field and Email field must be filled");
            return;
        }
        boolean success = InsertUser.addUser(name,email);
        outputArea.setText(success ? "User added!" : "Error adding user");
    }

    @FXML
    private void handleViewUsers()
    {
        StringBuilder sb = new StringBuilder();
        try(Connection conn = ConnectionDB.getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next())
            {
                sb.append(rs.getInt("id"))
                        .append(" | ").append(rs.getString("name"))
                        .append(" | ").append(rs.getString("email"))
                        .append("\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sb.append("Error");
        }
        outputArea.setText(sb.toString());
    }

    @FXML
    private void handleUpdateUser()
    {
        try
        {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            boolean success = UpdateUser.updateUser(id,name,email);
            outputArea.setText(success ? "User updated" : "Error");
        }
        catch(NumberFormatException e)
        {
            outputArea.setText("Enter valid ID in Name field");
        }
    }

    @FXML
    private void handleDeleteUser()
    {
        try
        {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            if(!name.isEmpty() || !email.isEmpty())
            {
                outputArea.setText("When deleting a user only ID must be filled.\nEverything else is deleted automatically.");
                return;
            }
            boolean success = DeleteUser.deleteUser(id);
            outputArea.setText(success ? "User deleted" : "Error");
        }
        catch(NumberFormatException e)
        {
            outputArea.setText("Enter valid ID");
        }
    }
}


