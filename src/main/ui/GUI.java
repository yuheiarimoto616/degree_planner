package ui;

import model.Course;
import model.DegreePlanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GUI extends MouseAdapter {
    private static final String JSON_STORE = "./data/degreeplanner.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private DegreePlanner degreePlanner;
    private JFrame frame;
    private DefaultTableModel model;
    private JTable courseTable;
    private JPanel panel;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton clearButton;
    private JTextField subjectCodeField;
    private JTextField courseCodeField;
    private JTextField gradeField;
    private JTextField statusField;
    private JTextField creditsField;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() {
        degreePlanner = new DegreePlanner();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        model = new DefaultTableModel();
        model.addColumn("Course");
        model.addColumn("Credits");
        model.addColumn("Status");
        model.addColumn("Grade(%)");
        model.addColumn("Letter Grade");

        courseTable = new JTable(model);
        courseTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        courseTable.setFillsViewportHeight(true);
        courseTable.setFont(new Font("Whitney", Font.PLAIN, 12));
        courseTable.addMouseListener(this);

        frame = new JFrame();
        frame.setSize(800, 800);

        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(0, 90, frame.getWidth(), 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(151, 212, 233), 4));

        panel = new HeaderPanel(frame);

        setUpTextFields();
        setUpButtons();
        setUpInstructions();

        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.add(panel);
        frame.add(scrollPane);
        frame.setTitle("Degree Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setFrameLocation(frame);

        new LoadingScreen(frame);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // MODIFIES: this
    // EFFECTS: set up buttons by adding action listener to them and adding them to frame
    public void setUpButtons() {
        createButtons();

        addButton.addActionListener(e -> addCourseOperation());
        clearButton.addActionListener(e -> clearFields());
        updateButton.addActionListener(e -> updateACourseOperation());
        deleteButton.addActionListener(e -> deleteCourseOperation());

        frame.add(loadButton);
        frame.add(saveButton);
        frame.add(addButton);
        frame.add(deleteButton);
        frame.add(updateButton);
        frame.add(clearButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates, set bounds and text of buttons that are used in degree planner
    public void createButtons() {
        loadButton = new LoadButton(this);
        saveButton = new SaveButton(this);
        addButton = new JButton();
        deleteButton = new JButton();
        updateButton = new JButton();
        clearButton = new JButton();

        addButton.setBounds(485, 500, 70, 40);
        deleteButton.setBounds(485, 560, 70, 40);
        updateButton.setBounds(485, 620, 70, 40);
        clearButton.setBounds(485, 680, 70, 40);

        addButton.setText("Add");
        deleteButton.setText("Delete");
        updateButton.setText("Update");
        clearButton.setText("Clear");
    }

    public void setUpInstructions() {
        JLabel subjectCode = new JLabel("<html><b>Subject Code:</b><br>(e.g. CPSC)</html>");
        JLabel courseCode = new JLabel("<html><b>Course Code:</b><br>(e.g. 210)</html>");
        JLabel grade = new JLabel("<html><b>Grade:</b><br>(NA for not completed)</html>");
        JLabel status = new JLabel("<html><b>Status:</b><br>(0: completed; 1: taking; 2: planning)</html>");
        JLabel credits = new JLabel("<html><b>Number of Credits:</b></html>");

        subjectCode.setBounds(185, 490, 145, 40);
        courseCode.setBounds(185, 540, 145, 40);
        grade.setBounds(185, 590, 145, 40);
        status.setBounds(185, 640, 145, 47);
        credits.setBounds(185, 690, 145, 40);


        frame.add(subjectCode);
        frame.add(courseCode);
        frame.add(grade);
        frame.add(status);
        frame.add(credits);
    }

    public void setUpTextFields() {
        subjectCodeField = new JTextField();
        courseCodeField = new JTextField();
        gradeField = new JTextField();
        statusField = new JTextField();
        creditsField = new JTextField();

        subjectCodeField.setBounds(335, 490, 120, 40);
        courseCodeField.setBounds(335, 540, 120, 40);
        gradeField.setBounds(335, 590, 120, 40);
        statusField.setBounds(335, 640, 120, 40);
        creditsField.setBounds(335, 690, 120, 40);

        frame.add(subjectCodeField);
        frame.add(courseCodeField);
        frame.add(gradeField);
        frame.add(statusField);
        frame.add(creditsField);
    }

    // MODIFIES: this
    // EFFECTS: set the location of the frame to the center of the screen
    public void setFrameLocation(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int xpos = (screenSize.width / 2) - (frameSize.width / 2);
        int ypos = (screenSize.height / 2) - (frameSize.height / 2);
        frame.setLocation(xpos, ypos);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int index = courseTable.getSelectedRow();

        subjectCodeField.setText(model.getValueAt(index, 0).toString().substring(0, 4));
        courseCodeField.setText(model.getValueAt(index, 0).toString().substring(5));
        if (model.getValueAt(index, 3).toString().equals("NA")) {
            gradeField.setText("NA");
        } else {
            gradeField.setText(model.getValueAt(index, 3).toString());
        }
        if (model.getValueAt(index, 2).toString().equals("Completed")) {
            statusField.setText("0");
        } else if ((model.getValueAt(index, 2).toString().equals("In progress"))) {
            statusField.setText("1");
        } else {
            statusField.setText("2");
        }
        creditsField.setText(model.getValueAt(index, 1).toString());
    }

    public void updateCourseList() {
        List<Course> listOfCourses = degreePlanner.getListOfCourses();

        model.setRowCount(0);
        for (Course course : listOfCourses) {
            addACourseToTable(course);
        }
    }

    public void addACourseToTable(Course course) {
        String courseName = course.getSubjectCode() + " " + course.getCourseCode();
        int credits = (int) course.getCredit();
        String status = course.getStatusInString();
        String grade;
        String letterGrade;
        if (course.getGrade() == -1) {
            grade = "NA";
            letterGrade = "NA";
        } else {
            grade = String.valueOf(course.getGrade());
            letterGrade = course.getLetterGrade();
        }

        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();

        Object[] courseData = {courseName, credits, status, grade, letterGrade};

        model.addRow(courseData);
    }

    public void addCourseOperation() {
        String subjectCode = subjectCodeField.getText().toUpperCase(Locale.ROOT);
        int courseCode = Integer.parseInt(courseCodeField.getText());
        int credits = Integer.parseInt(creditsField.getText());
        int status = Integer.parseInt(statusField.getText());

        Course course = new Course(subjectCode, courseCode, credits, status);

        if (!gradeField.getText().toLowerCase(Locale.ROOT).equals("na")) {
            course.setGrade(Integer.parseInt(gradeField.getText()));
        }

        degreePlanner.addCourse(course);
        addACourseToTable(course);

        clearFields();
    }

    public void updateACourseOperation() {
        int index = courseTable.getSelectedRow();

        Course course = degreePlanner.getListOfCourses().get(index);
        course.setSubjectCode(subjectCodeField.getText().toUpperCase(Locale.ROOT));
        course.setCourseCode(Integer.parseInt(courseCodeField.getText()));
        course.setCreditsNum(Integer.parseInt(creditsField.getText()));
        course.setStatus(Integer.parseInt(statusField.getText()));
        if (!gradeField.getText().toLowerCase(Locale.ROOT).equals("na")) {
            course.setGrade(Integer.parseInt(gradeField.getText()));
        } else {
            course.setGrade(-1);
        }

        updateCourseList();
        clearFields();
    }

    public void deleteCourseOperation() {
        int index = courseTable.getSelectedRow();

        degreePlanner.getListOfCourses().remove(index);
        updateCourseList();
    }

    public void clearFields() {
        subjectCodeField.setText("");
        courseCodeField.setText("");
        creditsField.setText("");
        statusField.setText("");
        gradeField.setText("");
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
        updateCourseList();
    }
}
