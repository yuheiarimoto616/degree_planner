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
    public Course(String subjectCode, int courseCode, int numCredits, int status, int grade) {
        this.subjectCode = subjectCode;
        this.courseCode = courseCode;
        this.credits = numCredits;
        this.status = status;
        this.grade = grade;
    }

    /*
     * EFFECTS: return status in string:
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
     * EFFECTS: return course name in the form "SubjectCode CourseCode" (e.g. CPSC 210)
     */
    public String getCourseName() {
        return getSubjectCode() + " " + getCourseCode();
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
     * REQUIRES: grade must be in range [-1, 100] (-1 represents NA)
     * MODIFIES: this
     * EFFECTS: if given grade is different from the current grade, sets course's grade to the given one and
     *          logs the update to the EventLog; otherwise, does nothing
     */
    public void setGrade(int grade) {
        if (this.grade != grade) {
            this.grade = grade;
            Event newEvent;
            if (grade == -1) {
                newEvent = new Event(getCourseName() + "'s grade changed to NA");
            } else {
                newEvent = new Event(getCourseName() + "'s grade changed to " + grade + "%");
            }
            EventLog.getInstance().logEvent(newEvent);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if given status is different from the current status, sets course's status to the give one
     *          and logs the update to the EventLog; otherwise, does nothing
     */
    public void setStatus(int status) {
        if (this.status != status) {
            this.status = status;
            Event newEvent = new Event(getCourseName() + "'s status changed to " + getStatusInString());
            EventLog.getInstance().logEvent(newEvent);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Based on given subjectCode and courseCode, if new courseName is
     *          different from the current courseName, updates subjectCode and courseCode
     *          and logs the update to the EventLog; otherwise, does nothing
     */
    public void setCourseName(String subjectCode, int courseCode) {
        if (!getCourseName().equals(subjectCode + " " + courseCode)) {
            String originalCourseName = getCourseName();
            this.subjectCode = subjectCode;
            this.courseCode = courseCode;
            Event newEvent = new Event(originalCourseName + " changed to " + getCourseName());
            EventLog.getInstance().logEvent(newEvent);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: If given credits number is different from the current credits number,
     *          sets course's credits to the given one and logs the update to the EventLog;
     *          otherwise, does nothing
     */
    public void setCreditsNum(int credits) {
        if (this.credits != credits) {
            this.credits = credits;
            Event newEvent = new Event(getCourseName() + "'s number of credits changed to " + credits);
            EventLog.getInstance().logEvent(newEvent);
        }
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


