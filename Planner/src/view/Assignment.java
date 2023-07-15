package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;

public class Assignment extends JPanel{
     private String myUsername;
     private String myProf;
     private int myPrio;
     private String myTitle;
     private Date myDate;
     private Time myStartTime;
     private Time myEndTime;


     public Assignment(String theUsername, String theTitle, String theProf, int thePrio, Date theDate, Time theStart, Time theEnd) {
          myUsername = theUsername;
          myTitle = theTitle;
          myProf = theProf;
          myPrio = thePrio;
          myDate = theDate;
          myStartTime = theStart;
          myEndTime = theEnd;

          start();
     }
     private void start() {

     }


}
