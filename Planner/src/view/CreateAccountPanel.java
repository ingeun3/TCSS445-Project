package view;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates panel that will display when creating new account.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */

public class CreateAccountPanel extends JPanel{
    /** The JTextField object for username input. */
    private JTextField usernameField;
    /** The JTextField object for username input. */
    private JPasswordField passwordField;
    /** The ok button to finalize user input. */
    private JButton myOkButton;

    /** The default  constructor for CreateAccountPanel */
    public CreateAccountPanel() {
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
        myOkButton =  new JButton("Ok");

        Dimension labelSize = new Dimension(120, 30);
        usernameLabel.setPreferredSize(labelSize);
        passwordLabel.setPreferredSize(labelSize);

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
        buttonPanel.add(myOkButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(buttonPanel, constraints);
    }

    /** The getter for the user input in the usernameField. */
    public String getUsername() {
        return usernameField.getText();
    }
    /** The getter for the user input in the passwordField. */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    /** The getter for the ok button. */
    public JButton getOkButton() {
        return myOkButton;
    }

}
