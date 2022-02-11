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
     * MODIFIES: this
     * EFFECTS: remove course at the index from the listOfCourses
     */
    public void deleteCourse(int index) {
        listOfCourses.remove(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: change the status of a course specified by index to given status
     */
    public void changeStatus(int index, int status) {
        listOfCourses.get(index).setStatus(status);
    }

    /*
     * MODIFIES: this
     * EFFECTS: change the grade of a course specified by index to given grade
     */
    public void changeGrade(int index, int grade) {
        listOfCourses.get(index).setGrade(grade);
    }

    /*
     * EFFECTS: calculate the average grade of all the courses completed
     */
    public double calculateAvgGrade() {
        double weightedSumGrades = 0;
        double sumCredits = 0;
        for (Course course: listOfCourses) {
            if (course.getStatus() == 0) {
                weightedSumGrades += course.getGrade() * course.getCredit();
                sumCredits += course.getCredit();
            }
        }
        return Math.round((weightedSumGrades / sumCredits) * 10.0) / 10.0;
    }

    /*
     * REQUIRES: course must already be in the listOfCourses
     * EFFECTS: get location (index) of course in the listOfCourses based on subjectCode and courseNum
     */
    public int getCourseIndex(String subjectCode, int courseNum) {
        int index = 0;
        for (Course course: listOfCourses) {
            if (course.getSubjectCode().equals(subjectCode)  && course.getCourseCode() == courseNum) {
                return index;
            }
            index += 1;
        }
        return index;
    }

    // getters
    public List<Course> getListOfCourses() {
        return listOfCourses;
    }
}
