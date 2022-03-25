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

// Graphical user interface for degree planner
public class GUI extends MouseAdapter {
    private static final String JSON_STORE = "./data/degreeplanner.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private DegreePlanner degreePlanner;

    private JFrame frame;
    private DefaultTableModel model;
    private JTable courseTable;
    private JTextField subjectCodeField;
    private JTextField courseCodeField;
    private JTextField gradeField;
    private JTextField statusField;
    private JTextField creditsField;

    // EFFECTS: instantiates degree planner, jsonReader, jsonWriter, and main frame to create a GUI for
    //          degree planner, and sets up the frame as well as loading screen
    public GUI() {
        degreePlanner = new DegreePlanner();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        frame = new JFrame();
        frame.setSize(800, 800);
        JLabel courseTableLabel = new JLabel("Your courses:");
        courseTableLabel.setBounds(8, 97, 120, 25);
        courseTableLabel.setFont(new Font("Whitney", Font.BOLD, 15));

        setUpTable();
        setUpButtons();
        setUpInstructions();
        setUpTextFields();

        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.white);
        frame.add(courseTableLabel);
        frame.add(new HeaderPanel(frame));
        frame.setTitle("Degree Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setFrameLocation(frame);

        new LoadingScreen(frame);
    }

    // MODIFIES: this
    // EFFECTS: instantiates and sets up table to display courses
    public void setUpTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Course");
        model.addColumn("Credits");
        model.addColumn("Status");
        model.addColumn("Grade(%)");
        model.addColumn("Letter Grade");

        courseTable = new JTable(model);
        courseTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        courseTable.setFillsViewportHeight(true);
        courseTable.setFont(new Font("Whitney", Font.PLAIN, 14));
        courseTable.setRowHeight(18);
        courseTable.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(0, 125, frame.getWidth(), 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(151, 212, 233), 4));

        frame.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: instantiates and sets up buttons by adding action listener to them and adding them to frame
    public void setUpButtons() {
        JButton loadButton = new LoadButton(this);
        JButton saveButton = new SaveButton(this);
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");
        JButton clearButton = new JButton("Clear");

        addButton.setBounds(505, 520, 70, 40);
        deleteButton.setBounds(505, 580, 70, 40);
        updateButton.setBounds(505, 640, 70, 40);
        clearButton.setBounds(505, 700, 70, 40);

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
    // EFFECTS: instantiates and sets up JLabels for instruction on adding course, and adds them to the frame
    public void setUpInstructions() {
        JLabel subjectCode = new JLabel("<html><b>Subject Code:</b><br>(e.g. CPSC)</html>");
        JLabel courseCode = new JLabel("<html><b>Course Code:</b><br>(e.g. 210)</html>");
        JLabel grade = new JLabel("<html><b>Grade:</b><br>(NA for not completed)</html>");
        JLabel status = new JLabel("<html><b>Status:</b><br>(0: completed; 1: taking; 2: planning)</html>");
        JLabel credits = new JLabel("<html><b>Number of Credits:</b></html>");

        subjectCode.setBounds(205, 510, 145, 40);
        courseCode.setBounds(205, 560, 145, 40);
        grade.setBounds(205, 610, 145, 40);
        status.setBounds(205, 660, 145, 47);
        credits.setBounds(205, 710, 145, 40);

        frame.add(subjectCode);
        frame.add(courseCode);
        frame.add(grade);
        frame.add(status);
        frame.add(credits);
    }

    // MODIFIES: this
    // EFFECTS: instantiates and sets up text fields for course data entry, and add them to the frame
    public void setUpTextFields() {
        subjectCodeField = new JTextField();
        courseCodeField = new JTextField();
        gradeField = new JTextField();
        statusField = new JTextField();
        creditsField = new JTextField();

        subjectCodeField.setBounds(355, 510, 120, 40);
        courseCodeField.setBounds(355, 560, 120, 40);
        gradeField.setBounds(355, 610, 120, 40);
        statusField.setBounds(355, 660, 120, 40);
        creditsField.setBounds(355, 710, 120, 40);

        frame.add(subjectCodeField);
        frame.add(courseCodeField);
        frame.add(gradeField);
        frame.add(statusField);
        frame.add(creditsField);
    }

    // MODIFIES: this
    // EFFECTS: updates the table listing courses based on listOfCourses of degree planner
    public void updateCourseList() {
        List<Course> listOfCourses = degreePlanner.getListOfCourses();

        model.setRowCount(0);
        for (Course course : listOfCourses) {
            addACourseToTable(course);
        }
    }

    // MODIFIES: this
    // EFFECTS: add a course to the table listing courses based on the given course
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

    // MODIFIES: this
    // EFFECTS: adds a course to the degree planner based on course data entered in text
    //          fields, and updates the table; all the fields are cleared after adding a course is completed
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

    // MODIFIES: this
    // EFFECTS: based on a row selected and updated course data entered in text fields,
    //          specified course in degree planner and the table are updated;
    //          all the fields are cleared after the operation
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

    // MODIFIES: this
    // EFFECTS: deletes course in degree planner based on a row selected,
    //          and updates the table, clearing all the text fields
    public void deleteCourseOperation() {
        int index = courseTable.getSelectedRow();

        degreePlanner.getListOfCourses().remove(index);
        updateCourseList();
        clearFields();
    }

    // MODIFIES: this
    // EFFECTS: clears all the text fields
    public void clearFields() {
        subjectCodeField.setText("");
        courseCodeField.setText("");
        creditsField.setText("");
        statusField.setText("");
        gradeField.setText("");
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

    // MODIFIES: this
    // EFFECTS: when mouse is clicked on one of the rows of the table,
    //          row data is reflected on all the text fields
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
