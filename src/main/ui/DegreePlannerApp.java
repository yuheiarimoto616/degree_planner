package ui;

import model.Course;
import model.DegreePlanner;

import java.util.Locale;
import java.util.Scanner;

public class DegreePlannerApp {
    private static DegreePlanner degreePlanner;
    private static Scanner scanner;
    private static Main main;

    public DegreePlannerApp() {
        degreePlanner = new DegreePlanner();
        scanner = new Scanner(System.in);
        main = new Main();

        Boolean isQuit = false;

        while (!isQuit) {
            main.printInstruction();

            String action = scanner.nextLine();

            if (action.equals("add")) {
                main.addOperation();
            } else if (action.equals("view")) {
                degreePlanner.printCourses();
            } else if (action.equals("grade")) {
                System.out.println(degreePlanner.calculateAvgGrade());
            } else if (action.equals("quit")) {
                isQuit = true;
            }
        }
    }

    // EFFECTS: print out the instruction
    public void printInstruction() {
        System.out.println("Please select an option:");
        System.out.println("    - add (to add course)");
        System.out.println("    - view (to view your courses)");
        System.out.println("    - grade (to get average grade)");
        System.out.println("    - GPA");
        System.out.println("    - quit");
    }

    public void addOperation() {
        System.out.println("Subject code (e.g. CPSC):");
        String subjectCode = scanner.nextLine().toUpperCase(Locale.ROOT);
        System.out.println("Course number (e.g. 210):");
        String courseNum = scanner.nextLine();
        System.out.println("Number of credits:");
        int credits = scanner.nextInt();
        System.out.println("Status (choose 0 for passed, 1 for currently taking, and 2 for planning):");
        int status = scanner.nextInt();
        degreePlanner.addCourse(new Course(subjectCode, courseNum, credits, status));
        String buffer = scanner.nextLine();
    }
}
