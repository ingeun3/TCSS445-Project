package view;

import javax.swing.*;
import java.awt.*;
/**
 * This class is a JFrame that will run the program.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class GUIFrame extends JFrame {
    /** The GUIFrame instance. */
    private static GUIFrame instance;

    /**
     * Initialize the NorthPanel object.
     */
    private JPanel myNorthPanel;
    /**
     * Initialize the CenterPanel Object.
     */
    private JComponent myCenterPanel;

    /**
     * Private constructor of the GameInterface (main GUI).
     *
     */
    private GUIFrame()  {
        super("Priority Planner");
        myCenterPanel = new JPanel();
    }

    /**
     * Returns the singleton instance of GUIFrame.
     *
     * @return the singleton instance
     */
    public static GUIFrame getInstance() {
        if (instance == null) {
            instance = new GUIFrame();
        }
        return instance;
    }

    /**
     * Starting the GUI
     */
    public void start() {
        ImageIcon icon = new ImageIcon("./up.png");
        Image largeImage = icon.getImage().getScaledInstance(15, -1,
                java.awt.Image.SCALE_SMOOTH);

        setIconImage(largeImage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Adds JPanel to the North region of the GUIFrame.
     * @param thePanel the Panel that will be added to the North of the JFrame.
     */
    public void setNorthPanel(final JPanel thePanel) {
        myNorthPanel = thePanel;
        getContentPane().add(myNorthPanel, BorderLayout.NORTH);
        thePanel.requestFocus();
        start();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
     /**
      * Adds JPanel to the Center region of the GUIFrame.
      * @param thePanel the GUI Component that will be added to the Center of the JFrame.
      */
     public void setCenter(final JComponent thePanel) {
         getContentPane().remove(myCenterPanel);
         myCenterPanel = thePanel;
         repaint();
         getContentPane().add(myCenterPanel, BorderLayout.CENTER);
         thePanel.requestFocus();
         start();
         setExtendedState(JFrame.MAXIMIZED_BOTH);
     }
}
