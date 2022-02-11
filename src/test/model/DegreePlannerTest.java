package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DegreePlannerTest {
    public DegreePlanner testDegreePlanner;
    public Course course1;
    public Course course2;
    public Course course3;
    public Course course4;
    public Course course5;

    @BeforeEach
    public void setUp() {
        testDegreePlanner = new DegreePlanner();
        course1 = new Course("CPSC", 210, 4, 2);
        course2 = new Course("PHYS", 100, 3, 0);
        course3 = new Course("PHYS", 170, 3, 1);
        course4 = new Course("DSCI", 100, 3, 1);
        course5 = new Course("ECON", 101, 3, 1);
        course2.setGrade(98);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testDegreePlanner.getListOfCourses().size());
    }

    @Test
    public void testAddCourse() {
        assertTrue(testDegreePlanner.getListOfCourses().isEmpty());
        testDegreePlanner.addCourse(course1);
        assertEquals(1, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        testDegreePlanner.addCourse(course2);
        assertEquals(2, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(1));
    }

    @Test
    public void testDeleteCourse() {
        testDegreePlanner.addCourse(course1);
        testDegreePlanner.addCourse(course2);
        testDegreePlanner.addCourse(course3);
        testDegreePlanner.addCourse(course4);
        testDegreePlanner.addCourse(course5);

        testDegreePlanner.deleteCourse(course4.getSubjectCode(), course4.getCourseCode());
        assertEquals(4, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(1));
        assertEquals(course3, testDegreePlanner.getListOfCourses().get(2));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(3));

        testDegreePlanner.deleteCourse(course3.getSubjectCode(), course3.getCourseCode());
        assertEquals(3, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(1));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(2));

        testDegreePlanner.deleteCourse(course1.getSubjectCode(), course1.getCourseCode());
        assertEquals(2, testDegreePlanner.getListOfCourses().size());
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(1));
    }

    @Test
    public void testCalculateAvgGrade() {

    }
}
