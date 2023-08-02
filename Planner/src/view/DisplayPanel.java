package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayPanel extends JTable {
    private JButton myDeleteButton;
    private JTable myTable;

    private JScrollPane myScrollPane;



    public DisplayPanel(ArrayList<Object[]> SQLData) {
        super();
        String[] columnNames = {"Title", "Due Date", "Priority", "Professor First Name", "Professor Last Name", "Start", "End", "Complete"};

        // Convert the ArrayList to a 2D array
        Object[][] data = new Object[SQLData.size()][];
        for (int i = 0; i < SQLData.size(); i++) {
            data[i] = SQLData.get(i);
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Override isCellEditable to selectively make cells editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for the "Age" column (column index 2)
                return column == 2;
            }
        };
        myTable = new JTable(model);
        myScrollPane = new JScrollPane(myTable);
        myDeleteButton = new JButton("Delete Selected Row");

    }

    public JButton getDeleteButton (){
        return myDeleteButton;
    }
    public JScrollPane getMyScrollPane() {
        return myScrollPane;
    }
}
