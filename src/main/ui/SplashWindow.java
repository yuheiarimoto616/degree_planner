package ui;

import javax.swing.*;
import java.awt.*;

// SplashWindow Demo
public class SplashWindow extends JWindow {
    private static final int PAUSE = 2000;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        final Runnable closerRunner = new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
            }
        };

        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(PAUSE);
                    SwingUtilities.invokeAndWait(closerRunner);
                } catch (Exception e) {
                    e.printStackTrace();
                    // can catch InvocationTargetException
                    // can catch InterruptedException
                }
            }
        };

        setVisible(true);

        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
}
