package view;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventCreatingFrame {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private JTextField titleField;
    private JTextField assignmentPriorityField;
    private JTextField dueDateField;
    private JTextField professorFirstNameField;
    private JTextField professorLastNameField;


    private boolean myOkayPressed;

    private String myUsername;

    private JButton myOkButton;

    private JFrame myFrame;

    private JPanel myPanel;


    public EventCreatingFrame() {
        myPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Title:");
        Font font = titleLabel.getFont().deriveFont(20f);
        titleLabel.setFont(font);
        titleField = new JTextField(20);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(font);
        dueDateField = new JTextField(20);

        JLabel assignmentPriorityLabel = new JLabel("Assignment Priority:");
        assignmentPriorityLabel.setFont(font);
        assignmentPriorityField = new JTextField(20);

        JLabel professorFirstNameLabel = new JLabel("Professor First Name:");
        professorFirstNameLabel.setFont(font);
        professorFirstNameField = new JTextField(20);

        JLabel professorLastNameLabel = new JLabel("Professor Last Name:");
        professorLastNameLabel.setFont(font);
        professorLastNameField = new JTextField(20);


        myOkButton =  new JButton("Ok");


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(215, 30);
        titleLabel.setPreferredSize(labelSize);
        assignmentPriorityLabel.setPreferredSize(labelSize);
        dueDateLabel.setPreferredSize(labelSize);
        professorFirstNameLabel.setPreferredSize(labelSize);
        professorLastNameLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(titleLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        myPanel.add(titleField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(dueDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(dueDateField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        myPanel.add(assignmentPriorityLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        myPanel.add(assignmentPriorityField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        myPanel.add(professorFirstNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        myPanel.add(professorFirstNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        myPanel.add(professorLastNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        myPanel.add(professorLastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);

        myFrame = new JFrame("Add");
        start();
    }
    private void start() {
        myFrame.add(myPanel);
        myFrame.setPreferredSize(new Dimension(600, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        ImageIcon icon = new ImageIcon("./up.png");
        Image largeImage = icon.getImage().getScaledInstance(15, -1,
                java.awt.Image.SCALE_SMOOTH);
        // Set the icon for the JFrame

        myFrame.setIconImage(largeImage);
      // Center the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    public JTextField getTitleField() {
        return titleField;
    }
    public JTextField getAssignmentPriorityField() {
        return assignmentPriorityField;
    }
    public JTextField getProfessorFirstNameField() {
        return professorFirstNameField;
    }
    public JTextField getProfessorLastNameField() {
        return professorLastNameField;
    }

    public java.sql.Date getDueDate() throws Exception {
        String dateString = dueDateField.getText();
        return convertStringToSqlDate(dateString);
    }
    public static java.sql.Date convertStringToSqlDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(strDate);
        return new java.sql.Date(parsedDate.getTime());
    }


    public java.sql.Time formatStringToSQLTime(String strTime) throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        java.util.Date parsedTime = timeFormat.parse(strTime);
        return new java.sql.Time(parsedTime.getTime());
    }
    public JButton getOkButton() {
        return myOkButton;
    }
    public void close() {
        myFrame.dispose();
    }
}