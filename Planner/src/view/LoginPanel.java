package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    private boolean myOkayPressed;

    private boolean myCreateAccountPressed;

    public LoginPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        Font font = usernameLabel.getFont().deriveFont(20f);
        usernameLabel.setFont(font);

        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);

        passwordField = new JPasswordField(20);
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                myOkayPressed = true;
            }
        });

        JButton newLogin = new JButton("Create an account");
        newLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                myCreateAccountPressed = true;
            }
        });

        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(120, 30);
        usernameLabel.setPreferredSize(labelSize);
        passwordLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(passwordField, constraints);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(newLogin);
        buttonPanel.add(okButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Full width for the button panel
        //constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        //constraints.weightx = 1.0; // Allow the panel to expand horizontally
        add(buttonPanel, constraints);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public boolean getOkayStatus() {
        boolean temp = myOkayPressed;
        myOkayPressed = false;
        return temp;
    }
    public boolean getCreateAccountStatus() {
        boolean temp = myCreateAccountPressed;
        myCreateAccountPressed = false;
        return temp;
    }

}
