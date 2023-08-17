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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is a controller class that connect with GUI and model.
 *
 * @author Ingeun Hwang, Khin Win
 */
public class  Controller{
    // initialized myLoginFlag.
    private boolean myLoginFlag;

    // initialized myMainPanelFlag.
    private boolean myMainPanelFlag;

    // initialized myCreateAccountFlag.
    private boolean myCreateAccountFlag;

    // initialized myFrame.
    private GUIFrame myFrame;

    // initialized myLoginPanel.
    private LoginPanel myLoginPanel;

    // initialized myMainPanel.
    private DisplayPanel myMainPanel;

    // initialized myCreateAccountPanel.
    private CreateAccountPanel myCreateAccountPanel;

    // initialized myEntry.
    private SearchEntryPanel myEntry;

    // initialized mySearchFrame.
    private SearchFrame mySearchFrame;

    // initialized ArrayList myData.
    private ArrayList<Object[]> myData;

    // initialized myMenuBar.
    private MenuBar myMenuBar;

    // initialized myUsername.
    private String myUsername;

    // initialized myPassword.
    private String myPassword;

    // initialized mySearchType.
    private int mySearchType;

    // initialized int value clicked.
    private int myClicked;


    /**
     * This is the constructor for Controller Class.
     */
    public Controller() {
        // assign the GUI Frame to myFrame.
        myFrame = GUIFrame.getInstance();
        // assign the LoginPanel to myLoginPanel.
        myLoginPanel = new LoginPanel();
        // assign the CreateAccountPanel to myCreateAccountPanel.
        myCreateAccountPanel = new CreateAccountPanel();
        //intialized mySearchType to 1.
        mySearchType = 1;
        // assign SearchFrame to mySearchFrame.
        mySearchFrame = new SearchFrame();
        // assign SearchEntryPanel to myEntry.
        myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : ");
        // assign myLoginFlag to true.
        myLoginFlag = true;
        // set the LoginPanel to the center of the frame.
        myFrame.setCenter(myLoginPanel);
        // assign MenuBar to myMenuBar.
        myMenuBar = new MenuBar();
        // assign myUsername and myPassword to empty string.
        myUsername = "";
        myPassword = "";
        // assign clicked to 1.
        myClicked = 1;
        // call the addingListenersToSearchPanel method.
        addingListenersToSearchPanel();
        // call the start method.
        start();
    }

    /**
     * This method has Listeners for all Jbuttons.
     */

    public void start() {

        // get action Listener from createButton.
        myLoginPanel.getCreateNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Login action here when the "Ok" button is clicked
                myLoginFlag = false;
                // set the CreateAccountPanel into GUI Frame.
                myFrame.setCenter(myCreateAccountPanel);
                // reset the Flag.
                myCreateAccountFlag = true;
                //remove all the buttons.
                myLoginPanel.getCreateNewButton().removeAll();
            }
        });
        myLoginPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform Login action here when the "Ok" button is clicked
                // if user clicks one time then call the 'if' statement.
                if (myClicked % 2 == 1) {
                    Login login; // initialized the Login class.
                    try {
                        // pass the username and password information to the login class.
                        login = new Login(myLoginPanel.getUsername(), myLoginPanel.getPassword());
                        myUsername = myLoginPanel.getUsername();
                        myPassword = myLoginPanel.getPassword();
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    // if user put wrong information then JOptionPane will pop up.
                    if (login.getMyOutput() == true) {
                        JOptionPane.showMessageDialog(myFrame,
                                "Username or password not existing");
                    } else {
                        // otherwise, load the JTable.
                        loadJTable();
                    }
                    myClicked++;
                } else {
                    myClicked++;
                }
                // remove all buttons.
                myLoginPanel.getOkButton().removeAll();
            }
        });

        // Add ActionListener to the "Ok" button in CreateAccountPanel
        myCreateAccountPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform NewUser action here when the "Ok" button is clicked
                try {
                    // call the createWarningMessage method like whether account has been created successfully or not.
                    createWarningMessage();
                    // remove the buttons.
                    myCreateAccountPanel.getOkButton().removeAll();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // get action listener for myHomeButton.
        myMenuBar.getMyHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if user click once then load the JTable.
                if (myClicked % 2 == 1) {
                    loadJTable();
                    myClicked++;
                } else {
                    myClicked++;
                }
            }
        });

        // get action listener for add button.
        myMenuBar.getMyAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if user click once then call EventCreatingFrame class.
                if (myClicked % 2 == 1) {
                    // initialized it.
                    EventCreatingFrame event = new EventCreatingFrame();

                    // get action listener for OK button.
                    event.getOkButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                // pass all the information to the CreateEvent class.
                                CreateEvent newEvent = new CreateEvent(myUsername, event.getTitleField().getText(), event.getDueDate(),
                                        event.getProfessorFirstNameField().getText(),
                                        event.getProfessorLastNameField().getText(), Integer.parseInt(event.getAssignmentPriorityField().getText()));
                                // load the Jtable.
                                loadJTable();
                                // dispose it.
                                event.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    myClicked++;
                } else {
                    myClicked++;
                }
            }
        });

        // get action listener for Delete Button.
        myMenuBar.getMyDeleteButton().addActionListener(new ActionListener() {

            // if user click once then call the 'if' statement.
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myClicked % 2 == 1) {
                    // get selected row information from Table.
                    int selectedRow = myMainPanel.getTable().getSelectedRow();
                    // get an information from the row that user selected.
                    Object selectedValue = myMainPanel.getTable().getValueAt(selectedRow, 0);
                    if (selectedRow != -1) {
                        // remove the row from Table.
                        myMainPanel.getModel().removeRow(selectedRow);
                        // call the SQLQueries class.
                        SQLQueries temp  =  new SQLQueries();
                        // remove the row.
                        temp.deleteRow(selectedValue);
                    } else {
                        // if user cannot remove successfully then pop up the Joption Pane.
                        JOptionPane.showMessageDialog(myFrame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    myClicked++;
                } else {
                    myClicked++;
                }
            }
        });

        // get action listener for edit button.
        myMenuBar.getMyEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myClicked % 2 == 1) {
                    // if user click once then get information about selectedRow and Column.
                    int selectedRow = myMainPanel.getTable().getSelectedRow();
                    int selectedColumn = myMainPanel.getTable().getSelectedColumn();
                    if (selectedRow != -1 && selectedColumn != -1) {
                        //assign the value to the myMainPanel.
                        Object newValue = myMainPanel.getTable().getValueAt(selectedRow, selectedColumn);
                        Object id = myMainPanel.getTable().getValueAt(selectedRow, 0);
                        // Perform the database update using SQL query
                        SQLQueries temp = new SQLQueries();
                        temp.editRow(id, selectedColumn, newValue);
                    }
                    // refresh and show the Table again.
                    loadJTable();
                    myClicked++;
                } else {
                    myClicked++;
                }
            }
        });

        // get action listener for search button.
        myMenuBar.getMySearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if user click once then
                if (myClicked % 2 == 1) {
                    // assign the information to the myEntry.
                    myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : ",
                            "Default Search");
                    // set the center of the Frame for myEntry.
                    mySearchFrame.setCenter(myEntry.getPanel());
                    addingListenersToSearchPanel();

                    // when user click okay
                    mySearchFrame.getOkButton().addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // see user click once or not.
                            if (myClicked % 2 == 1) {

                            try {
                                // if search type is equal to 1 then
                                if (mySearchType == 1) {
                                    // load the Default JTable.
                                    loadDefaultJTableOnSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                    // if search type is equal to 2 then
                                } else if (mySearchType == 2) {
                                    // load the Priority Search JTable.
                                    loadJTableOnPrioritySearch(Integer.parseInt(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                    // if search type is equal to 3 then
                                } else if (mySearchType == 3) {
                                    // load the Professor Search Table
                                    loadJTableOnProfessorSearch(myEntry.getMyFirstEntry(),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()),
                                            convertStringToSqlDate(myEntry.getMyThirdEntry()));
                                    // if search type is equal to 4 then
                                } else if (mySearchType == 4) {
                                    // load the Time Search Table.
                                    loadJTableOnTimeSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                    // if search type is equal to 5 then
                                } else if (mySearchType == 5) {
                                    // load the Complete Search Table.
                                    loadJTableOnCompleteSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                    // if search type is equal to 6 then
                                } else if (mySearchType == 6) {
                                    // load total time search table
                                    loadJTableOnTotalTimeSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                    // if search type is equal to 7 then
                                } else if (mySearchType == 7) {
                                    // load Total Assignment Count Table
                                    loadJTableOnTotalAssignmentCount(convertStringToSqlDate(myEntry.getMyFirstEntry()),
                                            convertStringToSqlDate(myEntry.getMySecondEntry()));
                                }
                                // dispose the frame.
                                mySearchFrame.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            myClicked++;
                            } else {
                                myClicked++;
                            }
                        }
                    });
                    myClicked++;
                } else {
                    myClicked++;
                }
            }
        });

        // get the action listener for hide detail button.
        myMenuBar.getMyHideDetailButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load the Hide JTable.
                loadJTableHidingDetail();
            }
        });
    }

    /**
     * This method contains the button listeners for Search Panel.
     */
    private void addingListenersToSearchPanel() {

        // get action listener for default search button.
        mySearchFrame.getDefaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass the information to the Search Panel.
                myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                , "Default Search");
                    // set the Panel into the center.
                    mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                    // assign the value as one.
                    mySearchType = 1;
            }
        });

        // get action listener for priority button.
        mySearchFrame.getPriorityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     // pass information into the search entry.
                     myEntry = new SearchEntryPanel("Priority : ", "Start Date (yyyy-mm-dd) : "
                                                    , "Search By Priority");
                     // set the Panel into the center.
                    mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as two.
                    mySearchType = 2;
            }
        });

        // get action listener for Professor Button.
        mySearchFrame.getProfessorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass the information.
                 myEntry = new SearchEntryPanel("Professor Last Name : ", "Start Date (yyyy-mm-dd) : "
                        ,"End Date (yyyy-mm-dd) : ", "Search By Professor");

                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as three.
                mySearchType = 3;
            }
        });

        // get action listener for Time button.
        mySearchFrame.getTimeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass information.
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                ,"Search By Time");
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as four.
                mySearchType = 4;
            }
        });

        // get action listener for complete button.
        mySearchFrame.getCompletedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass the information.
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                    ,"Search Completed Assignment");
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as five.
                mySearchType = 5;
            }
        });

        // get action listener for time button.
        mySearchFrame.getTotalTimeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass the information.
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                         ,"Search Total Hour");
                 mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as six.
                 mySearchType = 6;
            }
        });

        // get action listener for count button.
        mySearchFrame.getAssignmentCountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass the information.
                myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                        , "Search Total Assignment Count");

                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                // assign the value as seven.
                mySearchType = 7;
            }
        });

        // get action listener for longer than Avg Button.
        mySearchFrame.getLongerThanAvgButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load the Assignment Table
                loadJTableOnAssignmentTookLongerThanAvg();
                // dispose it.
                mySearchFrame.close();
            }
        });

        // get action listener for Professor button.
        mySearchFrame.getProfAvgButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load the Table.
                loadJTableOnProfAverage();
                // dispose it.
                mySearchFrame.close();
            }
        });
    }

    /**
     * This method convert String to SQL Date.
     * @param theStrDate theStartDate
     * @return the SQL Date
     * @throws ParseException
     */
    public static java.sql.Date convertStringToSqlDate(final String theStrDate) throws ParseException {
        // give the Format for date.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // changed into Java Date.
        java.util.Date parsedDate = dateFormat.parse(theStrDate);
        // return as a SQL Date.
        return new java.sql.Date(parsedDate.getTime());
    }

    /**
     * This method showed whether user created account successfully or not.
     * @throws ClassNotFoundException
     */
    private void createWarningMessage() throws ClassNotFoundException {
        // pass the information into the New User class.
        NewUser newUser = new NewUser(myCreateAccountPanel.getUsername(), myCreateAccountPanel.getPassword());
        // if user click once then
        if (myClicked % 2 == 1) {
            if (newUser.getMyOutput() == 1) {
                // if output equal to 1 then show JOptionPane
                JOptionPane.showMessageDialog(myFrame,
                        "Username already exist!");
                // if output equal to 2 then show JOptionPane
            } else if (newUser.getMyOutput() == 2) {
                JOptionPane.showMessageDialog(myFrame,
                        "Account created successfully!");
                myLoginFlag = true;
                myFrame.setCenter(myLoginPanel);
                myCreateAccountFlag = false;
                myCreateAccountPanel.getOkButton().removeAll();
            } else {
                JOptionPane.showMessageDialog(myFrame,
                        "Account failed to create");
            }
            myClicked++;

        } else {
            myClicked++;
        }

    }

    /**
     * This method Load the JTable and show the Default Information.
     */

    private void loadJTable() {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Convert LocalDateTime to java.sql.Date
            java.sql.Date currentDateAsSqlDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());

            // Add 7 days to the java.sql.Date
            long millisToAdd = 7L * 24 * 60 * 60 * 1000; // 7 days in milliseconds
            java.sql.Date newSqlDate = new java.sql.Date(currentDateAsSqlDate.getTime() + millisToAdd);
            myData = new SQLQueries().searchDefault(myUsername,  currentDateAsSqlDate, newSqlDate);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 1);
        myLoginFlag = false;
         myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
        myMainPanelFlag = true;

    }

    /**
     * This method load the J table for search button.
     * @param theStart theStart Date.
     * @param theEnd   theEnd Date.
     */

    private void loadDefaultJTableOnSearch(final Date theStart, final Date theEnd) {
        try {
            myData = new SQLQueries().searchDefault(myUsername,  theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 1);
        myLoginFlag = false;
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
        myMainPanelFlag = true;
    }

    /**
     * This method load information about the Priority Search button.
     * @param thePriority  thePriority of the assignment.
     * @param theStart     theStart Date.
     */
    private void loadJTableOnPrioritySearch(final int thePriority, final Date theStart) {
        try {
            myData = new SQLQueries().searchByPriority(myUsername, thePriority,theStart);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 2);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Professor Search Button.
     * @param theProfLastName  theprofessorLastName.
     * @param theStart   TheStart Date.
     * @param theEnd     TheEnd Date.
     */
    private void loadJTableOnProfessorSearch(final String theProfLastName, final Date theStart, final Date theEnd) {
        try {
            myData = new SQLQueries().searchByProfessor(myUsername, theProfLastName, theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 3);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Time Search Button.
     * @param theStart  theStartDate.
     * @param theEnd    theEndDate.
     */
    private void loadJTableOnTimeSearch(final Date theStart, final Date theEnd) {
        try {
            myData = new SQLQueries().searchByTimeSpentOnAssignment(myUsername, theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 4);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Complete Search Button.
     * @param theStart  theStartDate.
     * @param theEnd    theEndDate.
     */

    private void loadJTableOnCompleteSearch(final Date theStart,final Date theEnd) {
        try {
            myData = new SQLQueries().searchCompletedAssignment(myUsername, theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 5);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Time Search Button.
     * @param theStart  theStartDate.
     * @param theEnd    theEndDate.
     */
    private void loadJTableOnTotalTimeSearch(final Date theStart,final Date theEnd) {
        try {
            myData = new SQLQueries().searchByTotalTimeSpent(myUsername, theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 6);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Total Assignment Count Button.
     * @param theStart  theStartDate.
     * @param theEnd    theEndDate.
     */
    private void loadJTableOnTotalAssignmentCount(final Date theStart,final Date theEnd) {
        try {
            myData = new SQLQueries().searchNumberOfAssignment(myUsername, theStart, theEnd);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 7);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Hiding Detail Button.
     */
    private void loadJTableHidingDetail() {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Convert LocalDateTime to java.sql.Date
            java.sql.Date currentDateAsSqlDate = java.sql.Date.valueOf(currentDateTime.toLocalDate());

            myData = new SQLQueries().minimizeView(myUsername, currentDateAsSqlDate);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 2);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Assignemnt Took Longer than Avg Button.
     */
    private void loadJTableOnAssignmentTookLongerThanAvg() {
        try {
            myData = new SQLQueries().SearchAssignmentTookLongerThanAvg(myUsername);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 8);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

    /**
     * This method load the information about Professor Avg Button.
     */
    private void loadJTableOnProfAverage() {
        try {
            myData = new SQLQueries().avgTimeSpentForProf();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 9);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
        myFrame.setNorthPanel(myMenuBar);
    }

}
