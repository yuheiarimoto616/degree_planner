package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a course having subject code, class code, percentage and letter grade user got,
// status (0 for completed, 1 for in progress, or 2 for planning), and number of credits
public class Course implements Writable {
    private String subjectCode;      // subject code of a course (e.g. CPSC)
    private int courseCode;          // course code of a course (e.g. 210)
    private int grade;               // percentage grade user got on this course (in range [0, 100] or -1 for null)
    private int status;              // status (0 for completed, 1 for in progress, or 2 for planning)
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
        this.status = status;
        this.grade = -1;
    }

    /*
     * EFFECTS: return status in string ;
     *          0 to "Completed";
     *          1 to "In progress"
     *          2 to "Planning"
     */
    public String getStatusInString() {
        if (status == 0) {
            return "Completed";
        } else if (status == 1) {
            return "In progress";
        } else {
            return "Planning";
        }
    }

    /*
     * REQUIRES: grade must be in range [0, 100]
     * MODIFIES: this
     * EFFECTS: set course's grade and percentage grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /*
     * REQUIRES: grade must be in range [0, 100]
     * EFFECTS: return course's letter grade based on the percentage grade
     */
    public String percentageToLetterGrade(int grade) {
        if (90 <= grade) {
            return "A+";
        } else if (85 <= grade) {
            return "A";
        } else if (80 <= grade) {
            return "A-";
        } else if (76 <= grade) {
            return "B+";
        } else if (72 <= grade) {
            return "B";
        } else if (68 <= grade) {
            return "B-";
        } else if (64 <= grade) {
            return "C+";
        } else if (60 <= grade) {
            return "C";
        } else if (55 <= grade) {
            return "C-";
        } else if (50 <= grade) {
            return "D";
        } else {
            return "F";
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: set course's status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: set subjectCode to the given code
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    // MODIFIES: this
    // EFFECTS: set courseCode to the given code
    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    // MODIFIES: this
    // EFFECTS: set credits number to the given number
    public void setCreditsNum(int credits) {
        this.credits = credits;
    }

    // getters
    public Integer getGrade() {
        return grade;
    }

    public String getLetterGrade() {
        return percentageToLetterGrade(grade);
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public int getStatus() {
        return status;
    }

    public double getCredit() {
        return credits;
    }

    // EFFECTS: convert Course object into JSONObject and return it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subjectCode", subjectCode);
        json.put("courseCode", courseCode);
        json.put("grade", grade);
        json.put("status", status);
        json.put("credits", credits);
        return json;
    }
}
