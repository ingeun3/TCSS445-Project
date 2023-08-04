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
import java.sql.SQLException;
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
                    System.out.println(selectedRow);
                    if (selectedRow != -1) {
                        myMainPanel.getModel().removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });

        /*
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
         */




//        while (true) {
//            if (myLoginFlag) {
//                if (myLoginPanel.getCreateAccountStatus()) {
//                    myLoginFlag = false;
//                    myFrame.setCenter(myCreateAccountPanel);
//                    myCreateAccountFlag = true;
//                }
//            } else if (myCreateAccountFlag) {
//                if (myCreateAccountPanel.getOkayStatus()) {
//                    myCreateAccountFlag = false;
//                    myFrame.setCenter(myLoginPanel);
//                    myLoginFlag = true;
//                }
//            }
//        }


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
            myData = new SQLQueries().getAllEventForUser(myUsername);
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
