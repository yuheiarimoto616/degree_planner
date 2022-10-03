package ui;

import javax.swing.*;
import java.awt.*;

// Represents a load button that is used to load a degree planner user saved
public class LoadButton extends JButton {

    // EFFECTS: sets up a load button and action listener is added to it
    public LoadButton(GUI gui) {
        setText("Load");
        setBounds(170, 440, 80, 35);
        setForeground(new Color(12, 35, 68));
        setFont(new Font("Whitney", Font.BOLD, 17));
        setBorder(BorderFactory.createLineBorder(new Color(64, 180, 229), 2));

        addActionListener(e -> gui.loadDegreePlanner());
    }
}
