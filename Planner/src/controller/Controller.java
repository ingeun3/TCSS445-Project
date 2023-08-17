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

    private SearchFrame mySearchFrame;

    private ArrayList<Object[]> myData;

    private MenuBar myMenuBar;

    private String myUsername;

    private String myPassword;

    private int mySearchType;

    private SearchEntryPanel myEntry;
    private int clicked;
    public Controller() {
        myFrame = GUIFrame.getInstance();
        myLoginPanel = new LoginPanel();
        myCreateAccountPanel = new CreateAccountPanel();
        mySearchType = 1;
        //myMainPanel = new DisplayPanel();
        mySearchFrame = new SearchFrame();
        addingListenersToSearchPanel();

        myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : ");

        myLoginFlag = true;
        myFrame.setCenter(myLoginPanel);
        myMenuBar = new MenuBar();

        myUsername = "";
        myPassword = "";

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
        myMenuBar.getMyHomeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked % 2 == 1) {
                    loadJTable();
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });
        myMenuBar.getMyAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked % 2 == 1) {
                    EventCreatingFrame event = new EventCreatingFrame();
                    event.getOkButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                CreateEvent newEvent = new CreateEvent(myUsername, event.getTitleField().getText(), event.getDueDate(),
                                        event.getProfessorFirstNameField().getText(),
                                        event.getProfessorLastNameField().getText(), Integer.parseInt(event.getAssignmentPriorityField().getText()));
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

        myMenuBar.getMyApplyChangeButton().addActionListener(new ActionListener() {
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
                    loadJTable();
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
                    myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : ",
                            "Default Search");

                    mySearchFrame.setCenter(myEntry.getPanel());
                    addingListenersToSearchPanel();
                    mySearchFrame.getOkButton().addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (clicked % 2 == 1) {

                            try {

                                if (mySearchType == 1) {
                                    loadDefaultJTableOnSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                } else if (mySearchType == 2) {
                                    loadJTableOnPrioritySearch(Integer.parseInt(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                } else if (mySearchType == 3) {
                                    loadJTableOnProfessorSearch(myEntry.getMyFirstEntry(), convertStringToSqlDate(myEntry.getMySecondEntry()), convertStringToSqlDate(myEntry.getMyThirdEntry()));
                                } else if (mySearchType == 4) {
                                    loadJTableOnTimeSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                } else if (mySearchType == 5) {
                                    loadJTableOnCompleteSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                } else if (mySearchType == 6) {
                                    loadJTableOnTotalTimeSearch(convertStringToSqlDate(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                } else if (mySearchType == 7) {
                                    loadJTableOnTotalAssignmentCount(convertStringToSqlDate(myEntry.getMyFirstEntry()), convertStringToSqlDate(myEntry.getMySecondEntry()));
                                }

                                mySearchFrame.close();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            clicked++;
                            } else {
                                clicked++;
                            }
                        }
                    });
                    clicked++;
                } else {
                    clicked++;
                }
            }
        });

        myMenuBar.getMyHideDetailButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTableHidingDetail();
            }
        });


    }

    private void addingListenersToSearchPanel() {
        mySearchFrame.getDefaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                , "Default Search");

                    mySearchFrame.setCenter((JPanel) myEntry.getPanel());

                    mySearchType = 1;
            }
        });

        mySearchFrame.getPriorityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     myEntry = new SearchEntryPanel("Priority : ", "Start Date (yyyy-mm-dd) : "
                                                    , "Search By Priority");
                    mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                    mySearchType = 2;
            }
        });

        mySearchFrame.getProfessorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 myEntry = new SearchEntryPanel("Professor Last Name : ", "Start Date (yyyy-mm-dd) : "
                        ,"End Date (yyyy-mm-dd) : ", "Search By Professor");

                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                mySearchType = 3;
            }
        });

        mySearchFrame.getTimeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                ,"Search By Time");
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                mySearchType = 4;
            }
        });


        mySearchFrame.getCompletedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                    ,"Search Completed Assignment");
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                mySearchType = 5;
            }
        });

        mySearchFrame.getTotalTimeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                         ,"Search Total Hour");
                mySearchFrame.setCenter(new JPanel());
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                mySearchType = 6;
            }
        });

        mySearchFrame.getAssignmentCountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myEntry = new SearchEntryPanel("Start Date (yyyy-mm-dd) : ", "End Date (yyyy-mm-dd) : "
                                                        , "Search Total Assignment Count");
                mySearchFrame.setCenter(new JPanel());
                mySearchFrame.setCenter((JPanel) myEntry.getPanel());
                mySearchType = 7;
            }
        });

        mySearchFrame.getLongerThanAvgButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTableOnAssignmentTookLongerThanAvg();
                mySearchFrame.close();
            }
        });

        mySearchFrame.getProfAvgButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTableOnProfAverage();
                mySearchFrame.close();
            }
        });
    }

    public static java.sql.Date convertStringToSqlDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(strDate);
        return new java.sql.Date(parsedDate.getTime());
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
                myLoginFlag = true;
                myFrame.setCenter(myLoginPanel);
                myCreateAccountFlag = false;
                myCreateAccountPanel.getOkButton().removeAll();
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

    private void loadDefaultJTableOnSearch(Date theStart, Date theEnd) {
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

    private void loadJTableOnPrioritySearch(int thePriority, Date theStart) {
        try {

            myData = new SQLQueries().searchByPriority(myUsername, thePriority,theStart);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 2);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();

    }

    private void loadJTableOnProfessorSearch(String theProfLastName, Date theStart, Date theEnd) {
        try {

            myData = new SQLQueries().searchByProfessor(myUsername, theProfLastName, theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 3);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }

    private void loadJTableOnTimeSearch(Date theStart, Date theEnd) {
        try {

            myData = new SQLQueries().searchByTimeSpentOnAssignment(myUsername, theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 4);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();

    }

    private void loadJTableOnCompleteSearch(Date theStart, Date theEnd) {
        try {
            myData = new SQLQueries().searchCompletedAssignment(myUsername, theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 5);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }

    private void loadJTableOnTotalTimeSearch(Date theStart, Date theEnd) {
        try {
            myData = new SQLQueries().searchByTotalTimeSpent(myUsername, theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 6);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }

    private void loadJTableOnTotalAssignmentCount(Date theStart, Date theEnd) {
        try {
            myData = new SQLQueries().searchNumberOfAssignment(myUsername, theStart, theEnd);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 7);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }
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
    }

    private void loadJTableOnAssignmentTookLongerThanAvg() {
        try {

            myData = new SQLQueries().SearchAssignmentTookLongerThanAvg(myUsername);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 8);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }
    private void loadJTableOnProfAverage() {
        try {
            myData = new SQLQueries().avgTimeSpentForProf();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        myMainPanel = new DisplayPanel(myData, 9);
        myFrame.setCenter(myMainPanel.getMyScrollPane());
        myLoginPanel.getOkButton().removeAll();
    }

}
