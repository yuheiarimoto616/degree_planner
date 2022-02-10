package model;

public class Course {
    private String subjectCode;
    private String classCode;
    private Double grade;
    private String status;
    private int credits;

    public Course(String subjectCode, String classCode, int credits, int status) {
        this.subjectCode = subjectCode;
        this.classCode = classCode;
        this.credits = credits;
        if (status == 0) {
            this.status = "passed";
        } else if (status == 1) {
            this.status = "currently taking";
        } else {
            this.status = "planning";
        }
    }

    // getters
    public Double getGrade() {
        return grade;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getStatus() {
        return status;
    }

    public double getCredit() {
        return credits;
    }
}
