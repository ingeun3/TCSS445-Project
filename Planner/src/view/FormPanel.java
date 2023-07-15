package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class FormPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField professorField;

    public FormPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel professorLabel = new JLabel("Professor Name:");
        professorField = new JTextField();

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(professorLabel);
        add(professorField);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getProfessorName() {
        return professorField.getText();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getProfessorField() {
        return professorField;
    }
}

