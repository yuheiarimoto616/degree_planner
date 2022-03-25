package ui;

import javax.swing.*;
import java.awt.*;

public class SaveButton extends JButton {

    public SaveButton(GUI gui) {
        setText("Save");
        setBounds(550, 420, 80, 35);
        setForeground(new Color(12, 35, 68));
        setFont(new Font("Whitney", Font.BOLD, 17));
        setBorder(BorderFactory.createLineBorder(new Color(64, 180, 229), 2));

        addActionListener(e -> gui.saveDegreePlanner());
    }
}
