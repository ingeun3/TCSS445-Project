package view;

import javax.swing.*;
import java.awt.*;

public class SearchFrame {
    private JFrame myFrame;
    private JButton myOkButton;

    private JButton myDefaultButton;

    private JButton myPriorityButton;

    private JButton myProfessorButton;

    private JButton myTimeButton;

    private JButton myCompletedButton;

    private JButton myTotalTimeButton;

    private JButton myAssignmentCountButton;

    private JButton myLongerthanAvgButton;

    private JButton myProfAvgButton;

    private JPanel myPanel;
    public SearchFrame() {
        myFrame = new JFrame("Add");
        myOkButton =  new JButton("Ok");
        myPanel = new JPanel();
        //JPanel buttonPanel = new JPanel();
        myDefaultButton = new JButton("Default");
        myPriorityButton = new JButton("Priority");
        myProfessorButton = new JButton("Professor");
        myTimeButton = new JButton("Time"); // <- shows each assignment
        myCompletedButton = new JButton("Completed Only");
        myTotalTimeButton = new JButton("Total Time"); // <- shows how much time spent between dates
        myAssignmentCountButton = new JButton("Assignments Total");
        myLongerthanAvgButton = new JButton("Assignments Took Longer Than Average");
        myProfAvgButton = new JButton("Professor Average Time");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Use GridLayout with 2 rows and 1 column

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
        secondRowPanel.add(myLongerthanAvgButton);
        secondRowPanel.add(myProfAvgButton);

        // Add the rows to the main button panel
        buttonPanel.add(firstRowPanel);
        buttonPanel.add(secondRowPanel);

        myFrame.add(buttonPanel, BorderLayout.NORTH);
        myFrame.add(myPanel, BorderLayout.CENTER);

    }
    public void start() {
        myFrame.setPreferredSize(new Dimension(750, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();

        // Center the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);


        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        myFrame.pack();
        myFrame.setVisible(true);
    }

    public void setCenter (JPanel theEntry) {
        myFrame.getContentPane().remove(myPanel);
        myPanel = theEntry;
        myFrame.add(theEntry, BorderLayout.CENTER);
      //  myFrame.revalidate();
        myFrame.repaint();
        start();
    }

    public JButton getDefaultButton() {
        return myDefaultButton;
    }
    public JButton getPriorityButton() {
        return myPriorityButton;
    }
    public JButton getProfessorButton() {
        return myProfessorButton;
    }
    public JButton getTimeButton() {
        return myTimeButton;
    }
    public JButton getCompletedButton() {
        return myCompletedButton;
    }
    public JButton getTotalTimeButton() {
        return myTotalTimeButton;
    }
    public JButton getAssignmentCountButton() {
        return myAssignmentCountButton;
    }
    public JButton getLongerThanAvgButton() {
        return myLongerthanAvgButton;
    }
    public JButton getProfAvgButton() {
        return myProfAvgButton;
    }
    public JButton getOkButton() {
        return myOkButton;
    }

    public void close() {
        myFrame.dispose();
    }

}
