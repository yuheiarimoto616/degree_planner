package ui;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private Dimension frameSize;
    private Dimension panelSize;

    public HeaderPanel(JFrame frame) {
        frameSize = frame.getSize();

        setBackground(new Color(0, 167, 225));
        setBounds(0, 0, frameSize.width, 90);
        setLayout(null);

        panelSize = this.getSize();

        setBorderLine();
        setLabel();
    }

    public void setBorderLine() {
        JPanel borderLine = new JPanel();
        borderLine.setBounds(0, panelSize.height - 5, frameSize.width, 5);
        borderLine.setBackground(new Color(12, 35, 68));
        borderLine.setLayout(null);
        this.add(borderLine);
    }

    public void setLabel() {
        ImageIcon ubcLogo = new ImageIcon("data/ubcLogo.png");
        JLabel label = new JLabel("Degree Planner");
        label.setForeground(new Color(12, 35, 68));
        label.setFont(new Font("Whitney", Font.BOLD, 25));
        label.setIcon(ubcLogo);
        label.setIconTextGap(15);

        Dimension labelSize = label.getPreferredSize();
        label.setBounds(20, (panelSize.height / 2) - (labelSize.height / 2), labelSize.width, labelSize.height);
        label.setLayout(null);

        this.add(label);
    }
}
