package ui;

import javax.swing.*;
import java.awt.*;

// Represents a header panel that comes at the top of the frame to represent the application
public class HeaderPanel extends JPanel {
    private Dimension frameSize;
    private Dimension panelSize;

    // EFFECTS: sets up the header panel and sets up the borderline and label within the panel
    public HeaderPanel(JFrame frame) {
        frameSize = frame.getSize();

        setBackground(new Color(0, 167, 225));
        setBounds(0, 0, frameSize.width, 90);
        setLayout(null);

        panelSize = this.getSize();

        setBorderLine();
        setLabel();
    }

    // MODIFIES: this
    // EFFECTS: instantiates and sets up a borderline
    public void setBorderLine() {
        JPanel borderLine = new JPanel();
        borderLine.setBounds(0, panelSize.height - 7, frameSize.width, 7);
        borderLine.setBackground(new Color(12, 35, 68));
        borderLine.setLayout(null);
        this.add(borderLine);
    }

    // MODIFIES: this
    // EFFECTS: instantiates and sets up a label which includes the title and the logo of the application
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
