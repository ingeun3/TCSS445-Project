package view;

import javax.swing.*;
import java.awt.*;

public class SearchEntryPanel {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private JTextField myFirstEntry;
    private JTextField mySecondEntry;

    private JTextField myThirdEntry;


    private boolean myOkayPressed;

    private String myUsername;



    private JPanel myPanel;




    public SearchEntryPanel(String theFirstEntry, String theLabel) {
        myPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel startDateLabel = new JLabel(theFirstEntry);
        Font font = startDateLabel.getFont().deriveFont(20f);
        startDateLabel.setFont(font);
        myFirstEntry = new JTextField(20);

        JLabel searchLabel = new JLabel(theLabel);
        searchLabel.setFont(font);

        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(260, 30);
        startDateLabel.setPreferredSize(labelSize);
        searchLabel.setPreferredSize(new Dimension(300, 30));

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(searchLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(startDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(myFirstEntry, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        myPanel.add(buttonPanel, constraints);



    }


    public SearchEntryPanel(String theFirstEntry, String theSecondEntry, String theLabel) {
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

        JLabel searchLabel = new JLabel(theLabel);
        searchLabel.setFont(font);


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(260, 30);
        startDateLabel.setPreferredSize(labelSize);
        endDateLabel.setPreferredSize(labelSize);
        searchLabel.setPreferredSize(new Dimension(300, 30));

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(searchLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(startDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(myFirstEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        myPanel.add(endDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        myPanel.add(mySecondEntry, constraints);



        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        myPanel.add(buttonPanel, constraints);


    }

    public SearchEntryPanel(String theFirstEntry, String theSecondEntry, String theThirdEntry, String theLabel) {
        myPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel myFirstEntryLabel = new JLabel(theFirstEntry);
        Font font = myFirstEntryLabel.getFont().deriveFont(20f);
        myFirstEntryLabel.setFont(font);
        myFirstEntry = new JTextField(20);

        JLabel mySecondEntryLabel = new JLabel(theSecondEntry);
        mySecondEntryLabel.setFont(font);
        mySecondEntry = new JTextField(20);

        JLabel myThirdEntryLabel = new JLabel(theThirdEntry);
        myThirdEntryLabel.setFont(font);
        myThirdEntry = new JTextField(20);


        JLabel mySearchLabel = new JLabel(theLabel);
        mySearchLabel.setFont(font);


        // Set preferred size for labels to control their width
        Dimension labelSize = new Dimension(260, 30);
        myFirstEntryLabel.setPreferredSize(labelSize);
        mySecondEntryLabel.setPreferredSize(labelSize);
        myThirdEntryLabel.setPreferredSize(labelSize);
        mySearchLabel.setPreferredSize(new Dimension(300, 30));

        // Add components using GridBagConstraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        myPanel.add(mySearchLabel, constraints);


        constraints.gridx = 0;
        constraints.gridy = 1;
        myPanel.add(myFirstEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        myPanel.add(myFirstEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        myPanel.add(mySecondEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        myPanel.add(mySecondEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        myPanel.add(myThirdEntryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        myPanel.add(myThirdEntry, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2; // Full width for the button panel

        constraints.fill = GridBagConstraints.HORIZONTAL; // Make the panel fill horizontally
        constraints.weightx = 1.0; // Allow the panel to expand horizontally



    }

    public JPanel getPanel() {
        return myPanel;
    }

    public String getMyFirstEntry() throws Exception {
        return myFirstEntry.getText();
    }

    public String getMySecondEntry() throws Exception {
        return  mySecondEntry.getText();
    }
    public String getMyThirdEntry() throws Exception {
        return  myThirdEntry.getText();
    }


}