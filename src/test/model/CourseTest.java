package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    public Course course1;
    public Course course2;
    public Course course3;

    @BeforeEach
    public void setUp() {
        course1 = new Course("CPSC", 210, 4, 2);
        course2 = new Course("PHYS", 100, 3, 0);
        course3 = new Course("DSCI", 100, 3, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC", course1.getSubjectCode());
        assertEquals(210, course1.getCourseCode());
        assertEquals(4, course1.getCredit());
        assertEquals(2, course1.getStatus());

        assertEquals("PHYS", course2.getSubjectCode());
        assertEquals(100, course2.getCourseCode());
        assertEquals(3, course2.getCredit());
        assertEquals(0, course2.getStatus());

        assertEquals("DSCI", course3.getSubjectCode());
        assertEquals(100, course3.getCourseCode());
        assertEquals(3, course3.getCredit());
        assertEquals(1, course3.getStatus());
    }

    @Test
    public void testGetStatusInString() {
        assertEquals("Planning", course1.getStatusInString());
        assertEquals("Completed", course2.getStatusInString());
        assertEquals("In progress", course3.getStatusInString());
    }

    @Test
    public void testSetGrade() {

    }
}