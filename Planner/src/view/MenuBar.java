package view;

import javax.swing.*;

public class MenuBar extends JPanel{
    private JButton myAddButton;
    private JButton myDeleteButton;
    private JButton myEditButton;
    public MenuBar (){
        myAddButton = new JButton("Add");
        myDeleteButton = new JButton("Delete");
        myEditButton = new JButton("Edit");
        add(myAddButton);
        add(myDeleteButton);
        add(myEditButton);
    }

    public JButton getMyAddButton() {
        return myAddButton;
    }

}
