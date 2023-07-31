package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPanel extends JTable {
    private JButton myDeleteButton;
    public DisplayPanel(Object[][] SQLData) {
        super();
        String[] columnNames = {"Title", "Due Date", "Priority", "Professor", "Start", "End", "Complete"};
        DefaultTableModel model = new DefaultTableModel(SQLData, columnNames) {
            // Override isCellEditable to selectively make cells editable
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for the "Age" column (column index 2)
                return column == 2;
            }
        };
        myDeleteButton = new JButton("Delete Selected Row");
        start(model);
    }
    private void start(DefaultTableModel model) {


       // JTable table = new JTable(model);
   //     JScrollPane scrollPane = new JScrollPane(table);


//        frame.add(scrollPane, BorderLayout.CENTER);
//
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    model.removeRow(selectedRow);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
    }
    public JButton getDeleteButton (){
        return myDeleteButton;
    }
}
