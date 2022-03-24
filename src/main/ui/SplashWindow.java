package ui;

import javax.swing.*;
import java.awt.*;

public class SplashWindow extends JWindow {
    public SplashWindow(JFrame f) {
        super(f);
        JLabel splashLabel = new JLabel("<html>Welcome to<br>Degree Planner</html>", SwingConstants.CENTER);
        splashLabel.setForeground(Color.white);
        splashLabel.setFont(new Font("Whitney", Font.BOLD, 40));
        splashLabel.setIconTextGap(35);

        ImageIcon splashLogo = new ImageIcon("data/splashLogo.png");

        splashLabel.setIcon(splashLogo);

        getContentPane().add(splashLabel, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(12, 35, 68));
        this.setSize(600, 400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = splashLabel.getPreferredSize();
        setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
        setVisible(true);
        screenSize = null;
        labelSize = null;
    }
}
