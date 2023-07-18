package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel formPanel = new LoginPanel();

//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = formPanel.getUsername();
//                String password = formPanel.getPassword();
//                String professorName = formPanel.getProfessorName();
//
//                // Save username to MySQL database
//                saveToDatabase(username, "username_table");
//
//                // Save password to MySQL database
//                saveToDatabase(password, "password_table");
//
//                // Save professor name to MySQL database
//                saveToDatabase(professorName, "professor_table");
//
//                // Optional: Clear the form fields after saving
//                formPanel.getUsernameField().setText("");
//                formPanel.getPasswordField().setText("");
//                formPanel.getProfessorField().setText("");
//            }
//        });

        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(saveButton);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void saveToDatabase(String data, String tableName) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "your-username";
        String password = "your-password";

        String sql = "INSERT INTO " + tableName + " (data) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, data);
            statement.executeUpdate();

            System.out.println("Data saved to the " + tableName + " table in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
