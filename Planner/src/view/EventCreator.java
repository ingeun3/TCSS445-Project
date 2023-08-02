package view;

import javax.swing.*;
import java.awt.*;

public class EventCreator {
    private JTextField titleField;
    private JTextField assignmentPriorityField;
    private JTextField professorFirstNameField;
    private JTextField professorLastNameField;
    private JTextField startTimeField;
    private JTextField endTimeField;


    private boolean myOkayPressed;

    private String myUsername;

    private JButton myOkButton;

    private JFrame myFrame;

    private JPanel myPanel;


    public EventCreator() {
        myPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Title:");
        Font font = titleLabel.getFont().deriveFont(20f);
        titleLabel.setFont(font);
        titleField = new JTextField(20);

        JLabel assignmentPriorityLabel = new JLabel("Assignment Priority:");
        assignmentPriorityLabel.setFont(font);
        assignmentPriorityField = new JPasswordField(20);

        JLabel professorFirstNameLabel = new JLabel("Professor First Name:");
        professorFirstNameLabel.setFont(font);
        professorFirstNameField = new JPasswordField(20);

        JLabel professorLastNameLabel = new JLabel("Professor Last Name:");
        professorLastNameLabel.setFont(font);
        professorLastNameField = new JPasswordField(20);

        JLabel startTimeLabel = new JLabel("Start Time Label:");
        startTimeLabel.setFont(font);
        startTimeField = new JPasswordField(20);

        JLabel endTimeLabel = new JLabel("End Time Label:");
        endTimeLabel.setFont(font);
        endTimeField = new JPasswordField(20);

        myOkButton =  new JButton("Ok");

//            myOkButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(final ActionEvent e) {
//                    myOkayPressed = true;
//                }
//            });

        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(215, 30);
        titleLabel.setPreferredSize(labelSize);
        assignmentPriorityLabel.setPreferredSize(labelSize);
        professorFirstNameLabel.setPreferredSize(labelSize);
        professorLastNameLabel.setPreferredSize(labelSize);
        startTimeLabel.setPreferredSize(labelSize);
        endTimeLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(titleLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        myPanel.add(titleField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(assignmentPriorityLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(assignmentPriorityField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        myPanel.add(professorFirstNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        myPanel.add(professorFirstNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        myPanel.add(professorLastNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        myPanel.add(professorLastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        myPanel.add(startTimeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        myPanel.add(startTimeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        myPanel.add(endTimeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        myPanel.add(endTimeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        //   JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
//        buttonPanel.add(myOkButton);
       // myPanel.add(buttonPanel, constraints);

        myFrame = new JFrame("Add");
        start();
    }
    private void start() {
        myFrame.add(myPanel);
        myFrame.setPreferredSize(new Dimension(600, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();

      // Center the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        myFrame.pack();
        myFrame.setVisible(true);
    }

//    public String getUsername() {
//        return usernameField.getText();
//    }
//
//    public String getPassword() {
//        return new String(passwordField.getPassword());
//    }
//
//    public JTextField getUsernameField() {
//        return usernameField;
//    }
//
//    public JPasswordField getPasswordField() {
//        return passwordField;
//    }

    public JButton getOkButton() {
        return myOkButton;
    }
    public boolean getOkayStatus() {
        boolean temp = myOkayPressed;
        myOkayPressed = false;
        return temp;
    }
}
