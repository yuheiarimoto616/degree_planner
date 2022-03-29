package ui;

import model.Course;
import model.DegreePlanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

// Degree planner application
public class DegreePlannerApp {
    private static final String JSON_STORE = "./data/degreeplanner.json";
    private static DegreePlanner degreePlanner;
    private static Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the degree planner application
    public DegreePlannerApp() throws FileNotFoundException {
        degreePlanner = new DegreePlanner();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runDegreePlanner();
    }

    // MODIFIES: this
    // EFFECTS: process user inputs
    private void runDegreePlanner() {
        boolean isQuit = false;
        input = new Scanner(System.in);

        while (!isQuit) {
            printInstruction();

            String action = input.next().toLowerCase(Locale.ROOT);

            if (action.equals("add")) {
                addOperation();
            } else if (action.equals("delete")) {
                deleteOperation();
            } else if (action.equals("status")) {
                statusOperation();
            } else if (action.equals("view")) {
                printCourses();
            } else if (action.equals("grade")) {
                System.out.println("\n" + degreePlanner.calculateAvgGrade() + "%");
            } else if (action.equals("s")) {
                saveDegreePlanner();
            } else if (action.equals("l")) {
                loadDegreePlanner();
            } else if (action.equals("q")) {
                isQuit = true;
            }
        }
    }

    // EFFECTS: print out the instruction for user
    public void printInstruction() {
        System.out.println("\nPlease select an option:");
        System.out.println("    • Edit degree planner");
        System.out.println("          - add (to add course)");
        System.out.println("          - delete (to delete course)");
        System.out.println("          - status (to change status of course)");
        System.out.println("    • View degree planner");
        System.out.println("          - view (to view your courses)");
        System.out.println("          - grade (to get average grade)");
        System.out.println("    ✻ s (to save degree planner)");
        System.out.println("    ✻ l (to load saved degree planner)");
        System.out.println("    ✻ q (to quit)");
    }

    /*
     * MODIFIES: this
     * EFFECTS: print instruction to add a course and
     *          based on user input specified course is added to
     *          the list of courses in degree planner;
     *          user can keep adding courses as long as they input "y"
     */
    public void addOperation() {
        boolean keepAdding = true;

        while (keepAdding) {
            doAddingCourse();

            System.out.println("\ny -> to keep adding");
            System.out.println("n -> to stop adding");
            String command = input.next().toLowerCase(Locale.ROOT);

            if (command.equals("n")) {
                keepAdding = false;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: print instruction to add a course and
     *          based on user input, specified course is added to
     *          the list of courses in degree planner
     */
    private void doAddingCourse() {
        System.out.println("\nSubject code (e.g. CPSC):");
        String subjectCode = input.next().toUpperCase(Locale.ROOT);

        System.out.println("\nCourse number (e.g. 210):");
        int courseNum = input.nextInt();

        System.out.println("\nNumber of credits:");
        int credits = input.nextInt();

        System.out.println("\nStatus (choose 0 for completed, 1 for currently taking, and 2 for planning):");
        int status = input.nextInt();

        Course course = new Course(subjectCode, courseNum, credits, status, -1);

        if (status == 0) {
            System.out.println("\nGrade (%):");
            course.setGrade(input.nextInt());
        }

        degreePlanner.addCourse(course);
    }

    /*
     * MODIFIES: this
     * EFFECTS: print instruction to delete a course and
     *          based on user input specified course is deleted from
     *          the list of courses in degree planner
     */
    public void deleteOperation() {
        int courseIndex = getCourse();

        degreePlanner.deleteCourse(courseIndex);
    }

    /*
     * MODIFIES: this
     * EFFECTS: print instruction to change status of a course and
     *          based on user input, course's status is changed to status specified by user;
     *          if the status is changed to Completed, user is prompted to add grade for the course.
     */
    public void statusOperation() {
        int courseIndex = getCourse();

        System.out.println("\nChange status to ...");
        System.out.println("     - 0 -> Completed");
        System.out.println("     - 1 -> In progress");
        System.out.println("     - 2 -> Planning");
        int status = input.nextInt();

        degreePlanner.changeStatus(courseIndex, status);

        if (status == 0) {
            System.out.println("\nGrade (%):");
            int grade = input.nextInt();
            degreePlanner.changeGrade(courseIndex, grade);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: print instruction to get information of course in order to return
     *          index of the specified course in list of courses in DegreePlanner.
     */
    public int getCourse() {
        System.out.println("\nProvide the course's subject code:");
        String subjectCode = input.next().toUpperCase(Locale.ROOT);
        System.out.println("\nProvide the course's course number:");
        int courseNum = input.nextInt();

        return degreePlanner.getCourseIndex(subjectCode, courseNum);
    }

    /*
     * EFFECTS: print out all the courses in list of courses
     *          in bullet point with its number of credits, grade
     *          (if not completed NA), and its status
     */
    public void printCourses() {
        System.out.println("     Course      Credits      Status      Grade(%)      Letter Grade");
        for (Course course : degreePlanner.getListOfCourses()) {
            System.out.print("    " + course.getSubjectCode() + " " + course.getCourseCode() + "       ");
            System.out.print(course.getCredit());

            if (course.getStatus() == 0) {
                System.out.print("       " + course.getStatusInString() + "       ");
                System.out.print(course.getGrade() + "              " + course.getLetterGrade());
            } else if (course.getStatus() == 1) {
                System.out.print("       " + course.getStatusInString() + "     ");
                System.out.print("NA              NA");
            } else if (course.getStatus() == 2) {
                System.out.print("       " + course.getStatusInString() + "        ");
                System.out.print("NA              NA");
            }

            System.out.print("\n"); // to change line to print next course
        }
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
