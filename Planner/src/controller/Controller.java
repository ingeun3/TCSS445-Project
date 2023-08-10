package controller;

import model.CreateEvent;
import model.SQLQueries;
import model.Login;
import model.NewUser;
import view.*;
import view.MenuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class  Controller{
    private static final int LOGIN_PANEL = 0;
    private static final int CREATE_ACCOUNT_PANEL = 1;

    private boolean myLoginFlag;

    private boolean myMainPanelFlag;


    private boolean myCreateAccountFlag;

//    private static final int LOGIN_PANEL = 1;
    private GUIFrame myFrame;
    private LoginPanel myLoginPanel;

    private DisplayPanel myMainPanel;

    private CreateAccountPanel myCreateAccountPanel;

    private ArrayList<Object[]> myData;

    private MenuBar myMenuBar;

    private String myUsername;

    private String myPassword;

    private int clicked;
    public Controller() throws IOException, ClassNotFoundException, SQLException {
        myFrame = GUIFrame.getInstance();
        myLoginPanel = new LoginPanel();
        myCreateAccountPanel = new CreateAccountPanel();

        //myMainPanel = new DisplayPanel();




        myLoginFlag = true;
        myFrame.setCenter(myLoginPanel);
        myMenuBar = new MenuBar();

        myUsername = "";
        myPassword = "";

        clicked = 1;
        start();
    }

    public void start() throws ClassNotFoundException {


        myLoginPanel.getCreateNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Login action here when the "Ok" button is clicked
                myLoginFlag = false;
                myFrame.setCenter(myCreateAccountPanel);
                myCreateAccountFlag = true;
                myLoginPanel.getCreateNewButton().removeAll();
            }
        });


        myLoginPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Login action here when the "Ok" button is clicked
                if (clicked % 2 == 1) {
                    Login login;
                    try {
                        login = new Login(myLoginPanel.getUsername(), myLoginPanel.getPassword());
                        myUsername = myLoginPanel.getUsername();
                        myPassword = myLoginPanel.getPassword();
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (login.getMyOutput() == true) {
                        JOptionPane.showMessageDialog(myFrame,
                                "Username or password not existing");
                    } else {
                        loadJTable();
                    }
                    clicked++;
                } else {
                    clicked++;
                }
                myLoginPanel.getOkButton().removeAll();
            }
        });


        // Add ActionListener to the "Ok" button in CreateAccountPanel
        myCreateAccountPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform NewUser action here when the "Ok" button is clicked

                try {
                    createWarningMessage();
                    myCreateAccountPanel.getOkButton().removeAll();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        myMenuBar.getMyAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked % 2 == 1) {
                    EventCreatingPanel event = new EventCreatingPanel();
                    event.getOkButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                CreateEvent newEvent = new CreateEvent(myUsername, event.getTitleField().getText(), event.getDueDate(),
                                        event.getProfessorFirstNameField().getText(),
                                        event.getProfessorLastNameField().getText(), Integer.parseInt(event.getAssignmentPriorityField().getText()),
                                        event.getStartTimeField(), event.getEndTimeField());
                                loadJTable();
                                event.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });

        myMenuBar.getMyDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicked % 2 == 1) {
                    int selectedRow = myMainPanel.getTable().getSelectedRow();
                    Object selectedValue = myMainPanel.getTable().getValueAt(selectedRow, 0);
                    if (selectedRow != -1) {
                        myMainPanel.getModel().removeRow(selectedRow);
                        SQLQueries temp  =  new SQLQueries();
                        temp.deleteRow(selectedValue);
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });



        myMenuBar.getMyEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicked % 2 == 1) {
                    int selectedRow = myMainPanel.getTable().getSelectedRow();
                    int selectedColumn = myMainPanel.getTable().getSelectedColumn();

                    if (selectedRow != -1 && selectedColumn != -1) {
                        Object newValue = myMainPanel.getTable().getValueAt(selectedRow, selectedColumn);
                        Object id = myMainPanel.getTable().getValueAt(selectedRow, 0);
                        // Perform the database update using SQL query
                        SQLQueries temp = new SQLQueries();
                        temp.editRow(id, selectedColumn, newValue);
                    }
                    clicked++;
                } else {
                    clicked++;
                }
            }

        });

        myMenuBar.getMySearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked % 2 == 1) {
                    SearchPanel event = new SearchPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : ");
                    event.getOkButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                loadJTableOnSearch(event.getMyFirstEntry(), event.getMySecondEntry());
                                event.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });

    }

    private void createWarningMessage() throws ClassNotFoundException {
        NewUser newUser = new NewUser(myCreateAccountPanel.getUsername(), myCreateAccountPanel.getPassword());
        if (clicked % 2 == 1) {
            if (newUser.getMyOutput() == 1) {
                JOptionPane.showMessageDialog(myFrame,
                        "Username already exist!");
            } else if (newUser.getMyOutput() == 2) {
                JOptionPane.showMessageDialog(myFrame,
                        "Account created successfully!");
            } else {
                JOptionPane.showMessageDialog(myFrame,
                        "Account failed to create");
            }
            clicked++;

        } else {
            clicked++;
        }

    }

    private void loadJTable() {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Convert LocalDateTime to java.sql.Date
            java.sql.Date currentDateAsSqlDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());

            // Add 7 days to the java.sql.Date
            long millisToAdd = 7L * 24 * 60 * 60 * 1000; // 7 days in milliseconds
            java.sql.Date newSqlDate = new java.sql.Date(currentDateAsSqlDate.getTime() + millisToAdd);
            myData = new SQLQueries().getAllEventForUser(myUsername,  currentDateAsSqlDate, newSqlDate);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData);
        myLoginFlag = false;
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
        myMainPanelFlag = true;

    }

    private void loadJTableOnSearch(Date theStart, Date theEnd) {
        try {

            myData = new SQLQueries().getAllEventForUser(myUsername,  theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData);
        myLoginFlag = false;
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
        myMainPanelFlag = true;

    }


}
