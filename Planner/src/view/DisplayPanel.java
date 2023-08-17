package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
/**
 * This class creates JTable object that will display data
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class DisplayPanel extends JTable {
    /** The JTable that will display data from database. */
    private JTable myTable;
    /** The JScrollPane for JTable object. */
    private JScrollPane myScrollPane;
    /** The DefaultTableModel for JTable object. */
    private DefaultTableModel myModel;
    /** The Title of the columns. */
    private String[] columnNames;

    /**
     * The Default constructor for DisplayPanel object.
     * @param theSQLData The List of data from database
     * @param theType The type of JTable to display.
     */
    public DisplayPanel(final ArrayList<Object[]> theSQLData, final int theType) {
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
            columnNames = new String[]{"Professor First Name", "Professor Last Name", "Average Time Per Assignment"};
        }

        // Convert the ArrayList to a 2D array
        Object[][] data = new Object[theSQLData.size()][];
        for (int i = 0; i < theSQLData.size(); i++) {
            data[i] = theSQLData.get(i);
        }

        myModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2 || column ==  3 || column == 4 || column == 5 || column == 6 || column == 7
                        || column == 8;
            }
        };
        myTable = new JTable(myModel);
        myScrollPane = new JScrollPane(myTable);

    }

    /** The getter for myScrollPane. */
    public JScrollPane getMyScrollPane() {
        return myScrollPane;
    }
    /** The getter for myTable. */
    public JTable getTable() {
        return  myTable;
    }
    /** The getter for DefaultTableMode. */
    public DefaultTableModel getModel() {
        return myModel;
    }
}
