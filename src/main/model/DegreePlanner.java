package model;

import java.util.ArrayList;
import java.util.List;

public class DegreePlanner {
    private List<Course> listOfCourses;
    private int weightedGPA;
    private int numCredits;

    public DegreePlanner() {
        this.listOfCourses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add specified course to the listOfCourses
    public void addCourse(Course course) {
        listOfCourses.add(course);
    }

    // REQUIRES: course must be in the listOfCourses
    // MODIFIES: this
    // EFFECTS: remove course from the listOfCourses
    public void deleteCourse(Course course) {
        int index = 0;
        for (Course courseInList: listOfCourses) {
            if (courseInList == course) {
                listOfCourses.remove(index);
            }
            index += 1;
        }
    }

    // EFFECTS: calculate the average grade of all the courses completed
    public double calculateAvgGrade() {
        double weightedSumGrades = 0;
        double sumCredits = 0;
        for (Course course: listOfCourses) {
            if (course.getStatus() == "passed") {
                weightedSumGrades += course.getGrade() * course.getCredit();
                sumCredits += course.getCredit();
            }
        }
        return weightedSumGrades / sumCredits;
    }

    // EFFECTS: print out all the courses in list of courses in bullet point
    public void printCourses() {
        System.out.println("Courses:");
        for (Course course: listOfCourses) {
            System.out.println("  • " + course.getSubjectCode() + course.getClassCode() + "    " + course.getCredit());
        }
        System.out.println(" ");
    }


    // getters
    public List<Course> getListOfCourses() {
        return listOfCourses;
    }

    public double getWeightedGPA() {
        return weightedGPA;
    }
}
