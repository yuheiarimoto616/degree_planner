package ui;

import javax.swing.*;
import java.awt.*;

// Represents a loading screen that is shown before showing degree planner
public class LoadingScreen extends JWindow {

    // MODIFIES: this, frame
    // EFFECTS: create a loading screen that is shown on a screen for a while before showing frame
    public LoadingScreen(JFrame frame) {
        JLabel splashLabel = setUpLabel();

        getContentPane().add(splashLabel);
        getContentPane().setBackground(new Color(12, 35, 68));
        setSize(600, 400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = splashLabel.getPreferredSize();
        int xpos = screenSize.width / 2 - (labelSize.width / 2);
        int ypos = screenSize.height / 2 - (labelSize.height / 2) - 100;
        setLocation(xpos, ypos);

        setVisible(true);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
        frame.setVisible(true);
        dispose();
    }

    // EFFECTS: create a JLabel that will be shown on loading screen
    public JLabel setUpLabel() {
        JLabel splashLabel = new JLabel("<html>Welcome to<br>Degree Planner</html>", SwingConstants.CENTER);
        splashLabel.setForeground(Color.white);
        splashLabel.setFont(new Font("Whitney", Font.BOLD, 40));
        splashLabel.setIconTextGap(35);

        ImageIcon splashLogo = new ImageIcon("data/splashLogo.png");
        splashLabel.setIcon(splashLogo);

        return splashLabel;
    }
}
