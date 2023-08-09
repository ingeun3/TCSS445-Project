package view;

import javax.swing.*;

public class MenuBar extends JPanel{
    private JButton myAddButton;
    private JButton myDeleteButton;
    private JButton myEditButton;

    private JButton mySearchButton;

    public MenuBar (){
        myAddButton = new JButton("Add");
        myDeleteButton = new JButton("Delete");
        myEditButton = new JButton("Apply Changes");
        mySearchButton = new JButton("Search");
        add(myAddButton);
        add(myDeleteButton);
        add(myEditButton);
        add(mySearchButton);
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
}
