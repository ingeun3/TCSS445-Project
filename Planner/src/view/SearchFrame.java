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

    private JPanel myPanel;
    public SearchFrame() {
        myFrame = new JFrame("Add");
        myOkButton =  new JButton("Ok");
        myPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        myDefaultButton = new JButton("Default");
        myPriorityButton = new JButton("Priority");
        myProfessorButton = new JButton("Professor");
        myTimeButton = new JButton("Time"); // <- shows each assignment
        myCompletedButton = new JButton("Completed Only");
        myTotalTimeButton = new JButton("Total Time"); // <- shows how much time spent between dates

        buttonPanel.add(myDefaultButton);
        buttonPanel.add(myPriorityButton);
        buttonPanel.add(myProfessorButton);
        buttonPanel.add(myTimeButton);
        buttonPanel.add(myCompletedButton);
        buttonPanel.add(myTotalTimeButton);

        myFrame.add(buttonPanel, BorderLayout.NORTH);

        myFrame.add(myPanel, BorderLayout.CENTER);



    }
    public void start() {
        myFrame.setPreferredSize(new Dimension(600, 500));
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



    public JButton getOkButton() {
        return myOkButton;
    }
    public void close() {
        myFrame.dispose();
    }

}
