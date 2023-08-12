package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DisplayPanel extends JTable {
    private JTable myTable;

    private JScrollPane myScrollPane;

    private DefaultTableModel myModel;

    private String[] columnNames;

    public DisplayPanel(ArrayList<Object[]> SQLData, int theType) {
        super();
        if(theType == 1) {
            columnNames = new String[]{"Assignment ID", "Title", "Due Date", "Priority", "Professor First Name", "Professor Last Name", "Start", "End", "Complete"};
        } else if (theType == 2) {
            columnNames = new String[]{"Assignment ID", "Title",  "Priority", "Due Date"};
        } else if (theType == 3) {
            columnNames = new String[]{"Assignment ID", "Title"};
        } else if (theType == 4) {
            columnNames = new String[]{"Title", "Time"};
        } else if (theType == 5) {
            columnNames = new String[]{"Assignment ID", "Title", "Professor First Name", "Professor Last Name"};
        } else if (theType == 6) {
            columnNames = new String[]{"User Name", "Total Time"};
        } else if (theType == 7) {
            columnNames = new String[]{"User Name", "Total Assignment Count"};
        } else if (theType == 8) {
            columnNames = new String[]{"Assignment ID", "Title", "Professor First Name", "Professor Last Name", "Time"};
        } else if (theType == 9) {

        }



        // Convert the ArrayList to a 2D array
        Object[][] data = new Object[SQLData.size()][];
        for (int i = 0; i < SQLData.size(); i++) {
            data[i] = SQLData.get(i);
        }

        myModel = new DefaultTableModel(data, columnNames) {
            // Override isCellEditable to selectively make cells editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for the "Age" column (column index 2)
                return column == 1 || column == 2 || column ==  3 || column == 4 || column == 5 || column == 6 || column == 7
                        || column == 8;
            }
        };
        myTable = new JTable(myModel);
        myScrollPane = new JScrollPane(myTable);

    }

    public JScrollPane getMyScrollPane() {
        return myScrollPane;
    }

    public JTable getTable() {
        return  myTable;
    }
    public DefaultTableModel getModel() {
        return myModel;
    }
}
