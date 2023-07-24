package view;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    private JMenu addButton;
    private JMenu deleteButton;
    private JMenu editButton;
    public MenuBar (){
        addButton = new JMenu("Add");
        deleteButton = new JMenu("Delete");
        editButton = new JMenu("Edit");
        add(addButton);
        add(deleteButton);
        add(editButton);
    }

}
