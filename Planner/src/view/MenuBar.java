package view;

import javax.swing.*;

public class MenuBar extends JPanel{
    private JButton myAddButton;
    private JButton myDeleteButton;
    private JButton myEditButton;

    private JButton mySearchButton;

    private JButton myHideDetailButton;

    private JButton myAvgTimePerProfButton;

    private JButton mySearchAssignmentTookLongerButton;

    public MenuBar (){
        myAddButton = new JButton("Add");
        myDeleteButton = new JButton("Delete");
        myEditButton = new JButton("Apply Changes");
        mySearchButton = new JButton("Search");
        myHideDetailButton = new JButton("Hide Details");

        add(myAddButton);
        add(myDeleteButton);
        add(myEditButton);
        add(mySearchButton);
        add(myHideDetailButton);
    }

    public JButton getMyAddButton() {
        return myAddButton;
    }

    public JButton getMyDeleteButton() { return myDeleteButton; }

    public JButton getMyEditButton() {
        return myEditButton;
    }

    public JButton getMySearchButton() {
        return mySearchButton;
    }

    public JButton getMyHideDetailButton() {
        return myHideDetailButton;
    }
}
