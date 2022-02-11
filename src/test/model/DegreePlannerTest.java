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
        course3 = new Course("PHYS", 170, 3, 0);
        course4 = new Course("DSCI", 100, 4, 0);
        course5 = new Course("ECON", 101, 3, 1);
        course2.setGrade(98);
        course3.setGrade(80);
        course4.setGrade(91);
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


        testDegreePlanner.deleteCourse(3);
        assertEquals(4, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(1));
        assertEquals(course3, testDegreePlanner.getListOfCourses().get(2));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(3));

        testDegreePlanner.deleteCourse(2);
        assertEquals(3, testDegreePlanner.getListOfCourses().size());
        assertEquals(course1, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(1));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(2));

        testDegreePlanner.deleteCourse(0);
        assertEquals(2, testDegreePlanner.getListOfCourses().size());
        assertEquals(course2, testDegreePlanner.getListOfCourses().get(0));
        assertEquals(course5, testDegreePlanner.getListOfCourses().get(1));
    }

    @Test
    public void testChangeStatus() {
        testDegreePlanner.addCourse(course1);
        testDegreePlanner.addCourse(course2);
        testDegreePlanner.addCourse(course3);
        testDegreePlanner.addCourse(course4);
        testDegreePlanner.addCourse(course5);

        testDegreePlanner.changeStatus(0, 1);
        assertEquals(1, course1.getStatus());
        testDegreePlanner.changeStatus(0, 2);
        assertEquals(2, course1.getStatus());
        testDegreePlanner.changeStatus(3, 1);
        assertEquals(1, course4.getStatus());
        testDegreePlanner.changeStatus(3, 0);
        assertEquals(0, course4.getStatus());
    }

    @Test
    public void testChangeGrade() {
        testDegreePlanner.addCourse(course1);
        testDegreePlanner.addCourse(course2);
        testDegreePlanner.addCourse(course3);
        testDegreePlanner.addCourse(course4);
        testDegreePlanner.addCourse(course5);

        testDegreePlanner.changeGrade(1, 50);
        assertEquals(50, course2.getGrade());
        testDegreePlanner.changeGrade(3, 100);
        assertEquals(100, course4.getGrade());
    }

    @Test
    public void testGetCourseIndex() {
        testDegreePlanner.addCourse(course1);
        testDegreePlanner.addCourse(course2);
        testDegreePlanner.addCourse(course3);
        testDegreePlanner.addCourse(course4);
        testDegreePlanner.addCourse(course5);

        assertEquals(3, testDegreePlanner.getCourseIndex(course4.getSubjectCode(), course4.getCourseCode()));
        assertEquals(0, testDegreePlanner.getCourseIndex(course1.getSubjectCode(), course1.getCourseCode()));
        assertEquals(1, testDegreePlanner.getCourseIndex(course2.getSubjectCode(), course2.getCourseCode()));
        assertEquals(2, testDegreePlanner.getCourseIndex(course3.getSubjectCode(), course3.getCourseCode()));
        assertEquals(4, testDegreePlanner.getCourseIndex(course5.getSubjectCode(), course5.getCourseCode()));
        assertEquals(5, testDegreePlanner.getCourseIndex("WRDS", 100));
    }

    @Test
    public void testCalculateAvgGrade() {
        assertEquals(0, testDegreePlanner.calculateAvgGrade());

        testDegreePlanner.addCourse(course1);
        assertEquals(0, testDegreePlanner.calculateAvgGrade());

        testDegreePlanner.addCourse(course2);
        assertEquals(98, testDegreePlanner.calculateAvgGrade());

        testDegreePlanner.addCourse(course4);
        assertEquals(94, testDegreePlanner.calculateAvgGrade());

        testDegreePlanner.addCourse(course3);
        assertEquals(89.8, testDegreePlanner.calculateAvgGrade());
    }
}
