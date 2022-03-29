package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a degree planner having list of courses added by user
public class DegreePlanner implements Writable {
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
     * EFFECTS: add specified course to the listOfCourses;
     *          logs the event of adding the course to EventLog
     */
    public void addCourse(Course course) {
        listOfCourses.add(course);

        Event addEvent = new Event(course.getCourseName() + " added to Degree Planner.");
        EventLog.getInstance().logEvent(addEvent);
    }

    /*
     * MODIFIES: this
     * EFFECTS: remove course at the index from the listOfCourses;
     *          logs the event of deleting the course to EventLog
     */
    public void deleteCourse(int index) {
        String deletedCourse = listOfCourses.get(index).getCourseName();

        listOfCourses.remove(index);
        Event deleteEvent = new Event(deletedCourse + " deleted from Degree Planner");
        EventLog.getInstance().logEvent(deleteEvent);
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
     * EFFECTS: calculate the average grade of all the courses completed in the list of courses
     */
    public double calculateAvgGrade() {
        double weightedSumGrades = 0;
        double sumCredits = 0;
        for (Course course : listOfCourses) {
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
        for (Course course : listOfCourses) {
            if (course.getSubjectCode().equals(subjectCode) && course.getCourseCode() == courseNum) {
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

    // EFFECTS: convert DegreePlanner to JSONObject and return it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses in this degree planner as a JSON array
    public JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : listOfCourses) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
