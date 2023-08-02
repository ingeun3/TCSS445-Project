package view;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    private JMenu myAddButton;
    private JMenu myDeleteButton;
    private JMenu myEditButton;
    public MenuBar (){
        myAddButton = new JMenu("Add");
        myDeleteButton = new JMenu("Delete");
        myEditButton = new JMenu("Edit");
        add(myAddButton);
        add(myDeleteButton);
        add(myEditButton);
    }

    public JMenu getMyAddButton() {
        return myAddButton;
    }

}
