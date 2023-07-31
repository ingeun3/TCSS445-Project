package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditableJTableExample {

        public static void main(String[] args) {
            JFrame frame = new JFrame("Editable JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Sample data for the JTable
            Object[][] data = {
                    {1, "John", 25},
                    {2, "Alice", 32},
                    {3, "Bob", 28},
                    {4, "Eve", 30}
            };

            String[] columnNames = {"ID", "Name", "Age"};

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                // Override isCellEditable to selectively make cells editable
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Allow editing only for the "Age" column (column index 2)
                    return column == 2;
                }
            };

            JTable table = new JTable(model);


            JButton deleteButton = new JButton("Delete Selected Row");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        model.removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.add(deleteButton, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
        }

}
