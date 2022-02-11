package model;

// Represents a course having subject code, class code, grade user got,
// status (completed, in progress, or planning), and number of credits
public class Course {
    private String subjectCode;      // subject code of a course (e.g. CPSC)
    private int courseCode;       // course code of a course (e.g. 210)
    private Integer grade;           // percentage grade user got on this course
    private double gpa;              // gpa user got on this course
    private String status;           // status (completed, in progress, or planning)
    private int credits;             // number of credits the course worth

    /*
     * REQUIRES: status to be one of 0, 1, or 2
     * EFFECTS: subjectCode is set to subjectCode user input;
     *          courseCode is set to courseCode specified by user;
     *          credits is set to numCredits;
     *          status is set to "Completed" if status is 0, to "In progress" if status is 1,
     *          and to "Planning" if status is 2,
     */
    public Course(String subjectCode, int courseCode, int numCredits, int status) {
        this.subjectCode = subjectCode;
        this.courseCode = courseCode;
        this.credits = numCredits;
        if (status == 0) {
            this.status = "Completed";
        } else if (status == 1) {
            this.status = "In progress";
        } else {
            this.status = "Planning";
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: set course's grade and gpa based on the grade TODO
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    // getters
    public Integer getGrade() {
        return grade;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getStatus() {
        return status;
    }

    public double getCredit() {
        return credits;
    }
}
