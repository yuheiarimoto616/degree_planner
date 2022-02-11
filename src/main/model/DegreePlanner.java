package model;

import java.util.ArrayList;
import java.util.List;

// Represents a degree planner having courses added by user
public class DegreePlanner {
    private List<Course> listOfCourses; // list of courses added to the degree planner

    /*
     * EFFECTS: empty ArrayList for courses is set
     */
    public DegreePlanner() {
        this.listOfCourses = new ArrayList<>();
    }

    /*
     * REQUIRES: course should not be already in the listOfCourses
     * MODIFIES: this
     * EFFECTS: add specified course to the listOfCourses
     */
    public void addCourse(Course course) {
        listOfCourses.add(course);
    }

    /*
     * REQUIRES: course must be in the listOfCourses
     * MODIFIES: this
     * EFFECTS: remove course from the listOfCourses
     */
    public void deleteCourse(String subjectCode, int courseNum) {
        int index = 0;
        int courseIndex = 0;
        for (Course course: listOfCourses) {
            if (course.getSubjectCode().equals(subjectCode)  && course.getCourseCode() == courseNum) {
                courseIndex = index;
            }
            index += 1;
        }
        listOfCourses.remove(courseIndex);
    }

    /*
     * EFFECTS: calculate the average grade of all the courses completed
     */
    public double calculateAvgGrade() {
        double weightedSumGrades = 0;
        double sumCredits = 0;
        for (Course course: listOfCourses) {
            if (course.getStatus().equals("Completed")) {
                weightedSumGrades += course.getGrade() * course.getCredit();
                sumCredits += course.getCredit();
            }
        }
        return Math.round((weightedSumGrades / sumCredits) * 10.0) / 10.0;
    }


    // getters
    public List<Course> getListOfCourses() {
        return listOfCourses;
    }
}
