package controller;

import model.NewUser;
import view.GUIFrame;
import view.LoginPanel;

import java.io.IOException;

public class  Controller{
    private GUIFrame myFrame;
    private LoginPanel myLoginPanel;


    public Controller() throws IOException {
        myFrame = GUIFrame.getInstance();
        myLoginPanel = new LoginPanel();

        start();
    }
    public void start() {
        myFrame.setCenter(myLoginPanel);

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
