package controller;

import model.NewUser;
import view.CreateAccountPanel;
import view.GUIFrame;
import view.LoginPanel;

import java.io.IOException;

public class  Controller{
    private static final int LOGIN_PANEL = 0;
    private static final int CREATE_ACCOUNT_PANEL = 1;

    private boolean myLoginFlag;
    private boolean myCreateAccountFlag;

//    private static final int LOGIN_PANEL = 1;
    private GUIFrame myFrame;
    private LoginPanel myLoginPanel;

    private CreateAccountPanel myCreateAccountPanel;

    public Controller() throws IOException {
        myFrame = GUIFrame.getInstance();
        myLoginPanel = new LoginPanel();
        myCreateAccountPanel = new CreateAccountPanel();
        myLoginFlag = true;
        NewUser newUser = new NewUser(myCreateAccountPanel.getUsername(), myCreateAccountPanel.getPassword());

        start();
    }
    public void start() {
        myFrame.setCenter(myLoginPanel);
        while(true) {
            if(myLoginFlag) {
                System.out.println();
                if(myLoginPanel.getCreateAccountStatus() == true) {
                    myLoginFlag = false;

                    myFrame.setCenter(myCreateAccountPanel);
                    myCreateAccountFlag = true;
                }
            } else if (myCreateAccountFlag) {
                System.out.println();
                if(myCreateAccountPanel.getOkayStatus() == true) {
                    System.out.println("1324567");
                    //NewUser newUser = new NewUser(myCreateAccountPanel.getUsername(), myCreateAccountPanel.getPassword());
                   // System.out.println(myCreateAccountPanel.getUsername());

                }
            }

        }
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
