package ui;

import model.DegreePlanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {
    private static final String JSON_STORE = "./data/degreeplanner.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private DegreePlanner degreePlanner;

    private JFrame frame;
    private JPanel panel;
//    private JPanel borderLine;
//    private JLabel label;
    private JButton loadButton;
    private JButton saveButton;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() {
        degreePlanner = new DegreePlanner();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        frame = new JFrame();
        frame.setSize(800, 800);

        panel = new HeaderPanel(frame);
//        borderLine = new JPanel();
        loadButton = new LoadButton(this);
        saveButton = new SaveButton(this);

        Dimension frameSize = frame.getSize();

//        panel.setBackground(new Color(0, 167, 225));
//        panel.setBounds(0, 0, frameSize.width, 90);
//        panel.setLayout(null);

//        Dimension panelSize = panel.getSize();

//        borderLine.setBounds(0, panelSize.height - 5, frameSize.width, 5);
//        borderLine.setBackground(new Color(12, 35, 68));
//        borderLine.setLayout(null);

//        ImageIcon ubcLogo = new ImageIcon("data/ubcLogo.png");
//        label = new JLabel("Degree Planner");
//        label.setForeground(new Color(12, 35, 68));
//        label.setFont(new Font("Whitney", Font.BOLD, 25));
//        label.setIcon(ubcLogo);
//        label.setIconTextGap(15);
//        Dimension labelSize = label.getPreferredSize();
//        label.setBounds(20, (panelSize.height / 2) - (labelSize.height / 2), labelSize.width, labelSize.height);
//        label.setLayout(null);

        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.add(panel);
//        panel.add(label);
//        panel.add(borderLine);
        frame.add(loadButton);
        frame.add(saveButton);
        frame.setTitle("Degree Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setLocation(frame);

        new LoadingScreen(frame);
    }

    // MODIFIES: frame
    // EFFECTS: set the location of the frame to the center of the screen
    public void setLocation(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int xpos = (screenSize.width / 2) - (frameSize.width / 2);
        int ypos = (screenSize.height / 2) - (frameSize.height / 2);
        frame.setLocation(xpos, ypos);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // EFFECTS: if FileNotFoundException is caught, print a message;
    //          otherwise, saves the degree planner to file
    public void saveDegreePlanner() {
        try {
            jsonWriter.open();
            jsonWriter.write(degreePlanner);
            jsonWriter.close();
            System.out.println("Saved Degree Planner to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file; if IOException is caught, print a message.
    public void loadDegreePlanner() {
        try {
            degreePlanner = jsonReader.read();
            System.out.println("Loaded Degree Planner from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
