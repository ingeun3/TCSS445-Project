package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenHighlighter {
    private JFrame frame;
    private Rectangle region;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScreenHighlighter().start());
    }

    private void start() {
        frame = new JFrame("Screen Highlighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Set the specific region you want to monitor (e.g., x=100, y=100, width=200, height=200)
        region = new Rectangle(100, 100, 200, 200);

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (region.contains(e.getPoint())) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else {
                    frame.setCursor(Cursor.getDefaultCursor());
                }
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && region.contains(e.getPoint())) {
                    highlightRegion();
                }
            }
        });

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setUndecorated(true); // Remove window borders to achieve fullscreen effect
        frame.setVisible(true);
    }

    private void highlightRegion() {
        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(255, 0, 0, 100)); // Red with transparency (alpha=100)
                g2d.fillRect(region.x, region.y, region.width, region.height);
                g2d.setColor(Color.RED); // Border color (red)
                g2d.setStroke(new BasicStroke(3)); // Border thickness (3 pixels)
                g2d.drawRect(region.x, region.y, region.width, region.height); // Draw the border
                g2d.dispose();
            }
        };

        overlay.setOpaque(false);
        overlay.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Add the overlay to the frame's content pane
        frame.getContentPane().add(overlay);
        frame.repaint();
    }
}
