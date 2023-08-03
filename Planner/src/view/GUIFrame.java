package view;

import javax.swing.*;
import java.awt.*;

public class GUIFrame extends JFrame {

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
            super("Planner!");
            myCenterPanel = new JPanel();
          //  setIconImage(new ImageIcon("./resources/visuals/up.png").getImage());
        }

        /**
         * Returns the singleton instance of GameInterface.
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
//            setPreferredSize(new Dimension(800, 60));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
//            pack();
        }


        /**
         * Delete the Panel in the NorthRegion of the GameFrame
         */
        public void removeNorthPanel() {
            getContentPane().remove(myNorthPanel);
            revalidate();
            repaint();
        }

        /**
         * Adds JPanel to the North region of the GameFrame.
         * @param thePanel the Panel that will be added to the North of the JFrame.
         */
        public void setNorthPanel(final JPanel thePanel) {
           // removeNorthPanel();
            myNorthPanel = thePanel;
            getContentPane().add(myNorthPanel, BorderLayout.NORTH);
            thePanel.requestFocus();
            start();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        /**
         * Adds JPanel to the Center region of the GameFrame.
         * @param thePanel the Panel that will be added to the Center of the JFrame.
         */
        public void setCenter(final JComponent thePanel) {
            getContentPane().remove(myCenterPanel);
            myCenterPanel = thePanel;
            revalidate();
            repaint();
            getContentPane().add(myCenterPanel, BorderLayout.CENTER);
            thePanel.requestFocus();
            start();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

}
