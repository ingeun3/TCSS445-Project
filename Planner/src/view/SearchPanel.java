package view;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SearchPanel {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private JTextField myFirstEntry;
    private JTextField mySecondEntry;

    private JTextField myThirdEntry;


    private boolean myOkayPressed;

    private String myUsername;

    private JButton myOkButton;

    private JFrame myFrame;

    private JPanel myPanel;

    public SearchPanel(String theFirstEntry) {
        myPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel startDateLabel = new JLabel(theFirstEntry);
        Font font = startDateLabel.getFont().deriveFont(20f);
        startDateLabel.setFont(font);
        myFirstEntry = new JTextField(20);
        myOkButton =  new JButton("Ok");


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(215, 30);
        startDateLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(startDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        myPanel.add(myFirstEntry, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);

        myFrame = new JFrame("Add");
        start();
    }
    public SearchPanel(String theFirstEntry, String theSecondEntry) {
        myPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel startDateLabel = new JLabel(theFirstEntry);
        Font font = startDateLabel.getFont().deriveFont(20f);
        startDateLabel.setFont(font);
        myFirstEntry = new JTextField(20);

        JLabel endDateLabel = new JLabel(theSecondEntry);
        endDateLabel.setFont(font);
        mySecondEntry = new JTextField(20);

        myOkButton =  new JButton("Ok");


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(215, 30);
        startDateLabel.setPreferredSize(labelSize);
        endDateLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(startDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        myPanel.add(myFirstEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(endDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(mySecondEntry, constraints);



        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(myOkButton);
        myPanel.add(buttonPanel, constraints);

        myFrame = new JFrame("Add");
        start();
    }

    public SearchPanel(String theFirstEntry, String theSecondEntry, String theThirdEntry) {
        myPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel firstEntryLabel = new JLabel(theFirstEntry);
        Font font = firstEntryLabel.getFont().deriveFont(20f);
        firstEntryLabel.setFont(font);
        myFirstEntry = new JTextField(20);

        JLabel mySecondEntryLabel = new JLabel(theSecondEntry);
        mySecondEntryLabel.setFont(font);
        mySecondEntry = new JTextField(20);

        JLabel myThirdEntryLabel = new JLabel(theThirdEntry);
        mySecondEntryLabel.setFont(font);
        myThirdEntry = new JTextField(20);

        myOkButton =  new JButton("Ok");


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(215, 30);
        firstEntryLabel.setPreferredSize(labelSize);
        mySecondEntryLabel.setPreferredSize(labelSize);

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(firstEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        myPanel.add(myFirstEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(mySecondEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(mySecondEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        myPanel.add(mySecondEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        myPanel.add(mySecondEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
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

        // Center the JFrame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - myFrame.getWidth()) / 2;
        int centerY = (screenSize.height - myFrame.getHeight()) / 2;
        myFrame.setLocation(centerX, centerY);

        myFrame.pack();
        myFrame.setVisible(true);
    }


    public java.sql.Date getMyFirstEntry() throws Exception {
        String dateString = myFirstEntry.getText();
        return convertStringToSqlDate(dateString);
    }

    public java.sql.Date getMySecondEntry() throws Exception {
        String dateString = mySecondEntry.getText();
        return convertStringToSqlDate(dateString);
    }


    public static java.sql.Date convertStringToSqlDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(strDate);
        return new java.sql.Date(parsedDate.getTime());
    }

    public JButton getOkButton() {
        return myOkButton;
    }
    public void close() {
        myFrame.dispose();
    }
}