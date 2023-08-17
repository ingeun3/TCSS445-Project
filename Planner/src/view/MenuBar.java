package view;

import javax.swing.*;
/**
 * This class extends JPanel and display various buttons in the north region of the GUIFrame.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class MenuBar extends JPanel{
    /** The add button to open EventCreatingFrame. */
    private JButton myAddButton;
    /** The delete button that will delete the row of the JTable and in the database. */
    private JButton myDeleteButton;
    /** The apply change button that will save the change made in the JTabel. */
    private JButton myApplyChangeButton;
    /** The search button that will open search frame. */
    private JButton mySearchButton;
    /** The hide detail button that will display less information. */
    private JButton myHideDetailButton;
    /** The home button that will return to the default screen after search. */
    private JButton myHomeButton;

    /** The default contructor for Menubar object. */
    public MenuBar (){
        myHomeButton = new JButton ("Home");
        myAddButton = new JButton("Add");
        myDeleteButton = new JButton("Delete");
        myApplyChangeButton = new JButton("Apply Changes");
        mySearchButton = new JButton("Search");
        myHideDetailButton = new JButton("Hide Details");

        add(myHomeButton);
        add(myAddButton);
        add(myDeleteButton);
        add(myApplyChangeButton);
        add(mySearchButton);
        add(myHideDetailButton);
    }
    /** The getter for home button. */
    public JButton getMyHomeButton() {
        return myHomeButton;
    }
    /** The getter for add button. */
    public JButton getMyAddButton() {
        return myAddButton;
    }
    /** The getter for delete button. */
    public JButton getMyDeleteButton() { return myDeleteButton; }
    /** The getter for apply change button. */
    public JButton getMyApplyChangeButton() {
        return myApplyChangeButton;
    }
    /** The getter for search button. */
    public JButton getMySearchButton() {
        return mySearchButton;
    }
    /** The getter for hide detail button. */
    public JButton getMyHideDetailButton() {
        return myHideDetailButton;
    }
}
