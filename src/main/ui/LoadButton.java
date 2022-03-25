package ui;

import javax.swing.*;
import java.awt.*;

public class LoadButton extends JButton {

    public LoadButton(GUI gui) {
        setText("Load");
        setBounds(200, 600, 60, 25);
        setForeground(new Color(12, 35, 68));
        setFont(new Font("Whitney", Font.BOLD, 12));
        setBorder(BorderFactory.createLineBorder(new Color(64, 180, 229), 2));

        addActionListener(e -> gui.loadDegreePlanner());
    }
}
