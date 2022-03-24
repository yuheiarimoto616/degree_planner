package ui;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    public GUI() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0, 167, 225));

        ImageIcon ubcLogo = new ImageIcon("data/ubcLogo.png");
        label = new JLabel("Degree Planner");
        label.setForeground(new Color(12, 35, 68));
        label.setFont(new Font("Whitney", Font.BOLD, 25));
        label.setIcon(ubcLogo);
        label.setIconTextGap(15);


        frame.add(panel, BorderLayout.NORTH);
        panel.add(label);
        frame.setTitle("Degree Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 800);
        frame.setVisible(true);

        label.setLocation(label.getLocation().x - 250, label.getLocation().y);
        new SplashWindow(frame);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
