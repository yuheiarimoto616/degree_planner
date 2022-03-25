package ui;

import javax.swing.*;
import java.awt.*;

public class SaveButton extends JButton {

    public SaveButton(GUI gui) {
        setText("Save");
        setBounds(400, 600, 60, 25);
        setForeground(new Color(12, 35, 68));
        setFont(new Font("Whitney", Font.BOLD, 12));
        setBorder(BorderFactory.createLineBorder(new Color(64, 180, 229), 2));

        addActionListener(e -> gui.saveDegreePlanner());
    }
}
