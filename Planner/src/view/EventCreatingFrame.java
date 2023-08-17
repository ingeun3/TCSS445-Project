package view;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * This class creates JFrame to allow user to add assignment to the database.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class EventCreatingFrame {
    /** The date format. */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /** The JTextField object for title input. */
    private JTextField titleField;
    /** The JTextField object for assignment priority input. */
    private JTextField assignmentPriorityField;
    /** The JTextField object for due date input. */
    private JTextField dueDateField;
    /** The JTextField object for professor first name input. */
    private JTextField professorFirstNameField;
    /** The JTextField object for professor last name input. */
    private JTextField professorLastNameField;
    /** The ok button to finalize user input. */
    private JButton myOkButton;
    /** The JFrame to display the class. */
    private JFrame myFrame;
    /** The JPanel that will contain the layout of the frame. */
    private JPanel myPanel;

    /** The default constructor for EventCreatingFrame. */
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

        Dimension labelSize = new Dimension(215, 30);
        titleLabel.setPreferredSize(labelSize);
        assignmentPriorityLabel.setPreferredSize(labelSize);
        dueDateLabel.setPreferredSize(labelSize);
        professorFirstNameLabel.setPreferredSize(labelSize);
        professorLastNameLabel.setPreferredSize(labelSize);

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
        constraints.gridwidth = 2;

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);

        myFrame = new JFrame("Add");
        start();
    }

    /** Starts the GUI */
    private void start() {
        myFrame.add(myPanel);
        myFrame.setPreferredSize(new Dimension(600, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        ImageIcon icon = new ImageIcon("./up.png");
        Image largeImage = icon.getImage().getScaledInstance(15, -1,
                java.awt.Image.SCALE_SMOOTH);

        myFrame.setIconImage(largeImage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    /** Getter for input in the title field. */
    public JTextField getTitleField() {
        return titleField;
    }
    /** Getter for input in the assignment priority field. */
    public JTextField getAssignmentPriorityField() {
        return assignmentPriorityField;
    }
    /** Getter for input in the professor first name field. */
    public JTextField getProfessorFirstNameField() {
        return professorFirstNameField;
    }
    /** Getter for input in the professor last name field. */
    public JTextField getProfessorLastNameField() {
        return professorLastNameField;
    }
    /** Getter for input in the due date field. */
    public java.sql.Date getDueDate() throws Exception {
        String dateString = dueDateField.getText();
        return convertStringToSqlDate(dateString);
    }
    /** converts string input into SQL date format */
    public static java.sql.Date convertStringToSqlDate(final String theStringDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date parsedDate = dateFormat.parse(theStringDate);
        return new java.sql.Date(parsedDate.getTime());
    }
    /** Getter for Ok button. */
    public JButton getOkButton() {
        return myOkButton;
    }
    /** Closes the GUI*/
    public void close() {
        myFrame.dispose();
    }
}
