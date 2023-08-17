package view;

import javax.swing.*;
import java.awt.*;
/**
 * This class creates JFrame that will display search options.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class SearchFrame {
    /** The JFrame to diplay the search options. */
    private JFrame myFrame;
    /** The ok button to finalize user input. */
    private JButton myOkButton;
    /** The default search button input. */
    private JButton myDefaultButton;
    /** The priority search button input. */
    private JButton myPriorityButton;
    /** The professor search button input. */
    private JButton myProfessorButton;
    /** The time search button input. */
    private JButton myTimeButton;
    /** The completed search button input. */
    private JButton myCompletedButton;
    /** The total time search button input. */
    private JButton myTotalTimeButton;
    /** The assignment count search button input. */
    private JButton myAssignmentCountButton;
    /** The assignment took longer than average search button input. */
    private JButton myLongerThanAvgButton;
    /** The average assignment time for professor search button input. */
    private JButton myProfAvgButton;
    /** The JPanel that will lay out GUI Components. */
    private JPanel myPanel;
    /** The default constructor for SearchFrame object. */
    public SearchFrame() {
        myFrame = new JFrame("Search");
        myOkButton =  new JButton("Ok");
        myPanel = new JPanel();
        myDefaultButton = new JButton("Default");
        myPriorityButton = new JButton("Priority");
        myProfessorButton = new JButton("Professor");
        myTimeButton = new JButton("Time");
        myCompletedButton = new JButton("Completed Only");
        myTotalTimeButton = new JButton("Total Time");
        myAssignmentCountButton = new JButton("Assignments Total");
        myLongerThanAvgButton = new JButton("Assignments Took Longer Than Average");
        myProfAvgButton = new JButton("Professor Average Time");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

        // First row of buttons
        JPanel firstRowPanel = new JPanel();
        firstRowPanel.add(myDefaultButton);
        firstRowPanel.add(myPriorityButton);
        firstRowPanel.add(myProfessorButton);
        firstRowPanel.add(myTotalTimeButton);
        firstRowPanel.add(myAssignmentCountButton);
        firstRowPanel.add(myTimeButton);

        // Second row of buttons
        JPanel secondRowPanel = new JPanel();
        secondRowPanel.add(myCompletedButton);
        secondRowPanel.add(myLongerThanAvgButton);
        secondRowPanel.add(myProfAvgButton);

        // Add the rows to the main button panel
        buttonPanel.add(firstRowPanel);
        buttonPanel.add(secondRowPanel);

        myFrame.add(buttonPanel, BorderLayout.NORTH);
        myFrame.add(myPanel, BorderLayout.CENTER);

    }
    /** Starts the GUI. */
    public void start() {
        myFrame.setPreferredSize(new Dimension(750, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);

        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./up.png");
        Image largeImage = icon.getImage().getScaledInstance(15, -1,
                java.awt.Image.SCALE_SMOOTH);

        myFrame.setIconImage(largeImage);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    /** Set new GUI component in the center of the frame. */
    public void setCenter (JPanel theEntry) {
        myFrame.getContentPane().remove(myPanel);
        myPanel = theEntry;
        myFrame.add(theEntry, BorderLayout.CENTER);
        myFrame.repaint();
        start();
    }
    /** Getter for default search button. */
    public JButton getDefaultButton() {
        return myDefaultButton;
    }
    /** Getter for priority search button. */
    public JButton getPriorityButton() {
        return myPriorityButton;
    }
    /** Getter for professor search button. */
    public JButton getProfessorButton() {
        return myProfessorButton;
    }
    /** Getter for time search button. */
    public JButton getTimeButton() {
        return myTimeButton;
    }
    /** Getter for completed assignment search button. */
    public JButton getCompletedButton() {
        return myCompletedButton;
    }
    /** Getter for total time search button. */
    public JButton getTotalTimeButton() {
        return myTotalTimeButton;
    }
    /** Getter for assignment count search button. */
    public JButton getAssignmentCountButton() {
        return myAssignmentCountButton;
    }
    /** Getter for assignment took longer than average search button. */
    public JButton getLongerThanAvgButton() {
        return myLongerThanAvgButton;
    }
    /** Getter for average time for professor search button. */
    public JButton getProfAvgButton() {
        return myProfAvgButton;
    }
    /** Getter for ok button. */
    public JButton getOkButton() {
        return myOkButton;
    }
    /** Closes GUI. */
    public void close() {
        myFrame.dispose();
    }

}
