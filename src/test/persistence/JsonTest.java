package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(int credits, int courseCode, int grade, String subjectCode, int status, Course course) {
        assertEquals(credits, course.getCredit());
        assertEquals(courseCode, course.getCourseCode());
        assertEquals(grade, course.getGrade());
        assertEquals(subjectCode, course.getSubjectCode());
        assertEquals(status, course.getStatus());
    }
}
