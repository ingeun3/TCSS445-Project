package view;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates panel that will allow user to input entry for the search frame.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class SearchEntryPanel {

    /** The JTextField object for first entry input. */
    private JTextField myFirstEntry;
    /** The JTextField object for second entry input. */
    private JTextField mySecondEntry;
    /** The JTextField object for third entry input. */
    private JTextField myThirdEntry;
    /** The JPanel that will contain the layout of the GUI. */
    private JPanel myPanel;

    /**
     * The constructor for SearchEntryPanel with single entry.
     * @param theFirstEntry The user input for first entry.
     * @param theLabel The label of the search
     */
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

        Dimension labelSize = new Dimension(260, 30);
        startDateLabel.setPreferredSize(labelSize);
        searchLabel.setPreferredSize(new Dimension(300, 30));

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
        constraints.gridwidth = 2;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        myPanel.add(buttonPanel, constraints);

    }

    /**
     * The constructor for SearchEntryPanel with two entries.
     * @param theFirstEntry The user input for first entry
     * @param theSecondEntry The user input for second entry
     * @param theLabel The label of the search
     */
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

        Dimension labelSize = new Dimension(260, 30);
        startDateLabel.setPreferredSize(labelSize);
        endDateLabel.setPreferredSize(labelSize);
        searchLabel.setPreferredSize(new Dimension(300, 30));

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
        constraints.gridwidth = 2;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        myPanel.add(buttonPanel, constraints);


    }

    /**
     * The constructor for SearchEntryPanel with three entries.
     * @param theFirstEntry The user input for first entry.
     * @param theSecondEntry The user input for second entry.
     * @param theThirdEntry The user input for third entry.
     * @param theLabel The label for search.
     */
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

        Dimension labelSize = new Dimension(260, 30);
        myFirstEntryLabel.setPreferredSize(labelSize);
        mySecondEntryLabel.setPreferredSize(labelSize);
        myThirdEntryLabel.setPreferredSize(labelSize);
        mySearchLabel.setPreferredSize(new Dimension(300, 30));

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
        constraints.gridwidth = 2;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

    }

    /** Getter for myPanel. */
    public JPanel getPanel() {
        return myPanel;
    }
    /** Getter for user input in the first entry. */
    public String getMyFirstEntry() {
        return myFirstEntry.getText();
    }
    /** Getter for user input in the second entry. */
    public String getMySecondEntry() {
        return  mySecondEntry.getText();
    }
    /** Getter for user input in the third entry. */
    public String getMyThirdEntry() {
        return  myThirdEntry.getText();
    }

}