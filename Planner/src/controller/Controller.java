package controller;

import model.Login;
import model.NewUser;
import view.*;
import view.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    private MenuBar myMenuBar;

    private int clicked;
    public Controller() throws IOException, ClassNotFoundException {
        myFrame = GUIFrame.getInstance();
        myLoginPanel = new LoginPanel();
        myCreateAccountPanel = new CreateAccountPanel();
        Object[][] temp = {
                {"Unit 4 assignment","July 31, 2023", 4, "Tom Capaul", 0,0, false}
        };

        myMainPanel = new DisplayPanel(temp);
        myLoginFlag = true;
        myFrame.setCenter(myLoginPanel);
        myMenuBar = new MenuBar();

        clicked = 1;
        start();
    }
    public void start() {


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
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (login.getMyOutput() == true) {
                        JOptionPane.showMessageDialog(myFrame,
                                "Username or password not existing");
                    } else {
                        myLoginFlag = false;
                        myFrame.setCenter(myMainPanel.getMyScrollPane());
                        myLoginPanel.getOkButton().removeAll();
                        myFrame.setJMenuBar(myMenuBar);
                        myMainPanelFlag = true;

                        clicked++;
                    }
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

                    myCreateAccountPanel.getOkButton().removeAll();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

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


    // Gets the input from view code.

    // check if the user is logging in or making new account

    // if (UserName != null && password != null && user clicked enter)
        // then call the Login(username, password)
            // if (true) -> call event class (Event(username)) then make transition to main page
            // else -> make JOptionPane giving message saying "username or password not matching"

    // else if -> (user clicked new account)
        // then make transition to new login screen
            // if (UserName != null && password != null && user clicked enter)
            // then call the NewUser(username, password)
            // Make transition to the main page










    //  getTitles(Ingeun)
    //    -> Returns [390QUiz, SQLHW, AlgUnit2HW]             // We know the username and assignment titles

    // Create EventButton Class {
    //  myAssignmentID;
    //  ...
    //
    //  for(String title : getTitle(Ingeun) {
    //       int assignmentId = getAssignmentID(Ingeun, title);
    //       getPrio(assignmentId);
    //       getTime(assignmentId);
    //       ...
    //
    //  }
    // }

    //  getAssignmentID(Ingeun, 390Quiz)
    //    -> Returns AssignmentId                             // Now that we know the asssignment ID of this specific assignment,
                                                              //    we can retrieve all the other datas.







}
