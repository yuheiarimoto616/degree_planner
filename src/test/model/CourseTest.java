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
        course2.setGrade(95);
        assertEquals(95, course2.getGrade());
        assertEquals("A+", course2.getLetterGrade());

        course2.setGrade(80);
        assertEquals(80, course2.getGrade());
        assertEquals("A-", course2.getLetterGrade());
    }

    @Test
    public void testSetSubjectCode() {
        course2.setSubjectCode("ECON");
        assertEquals("ECON", course2.getSubjectCode());

        course1.setSubjectCode("PHYS");
        assertEquals("PHYS", course1.getSubjectCode());
    }

    @Test
    public void testSetCourseCode() {
        course1.setCourseCode(310);
        assertEquals(310, course1.getCourseCode());

        course2.setCourseCode(170);
        assertEquals(170, course2.getCourseCode());
    }

    @Test
    public void testSetCreditNum() {
        course1.setCreditsNum(3);
        assertEquals(3, course1.getCredit());

        course2.setCreditsNum(1);
        assertEquals(1, course2.getCredit());
    }

    @Test
    public void testPercentageToLetterGrade() {
        assertEquals("A+", course1.percentageToLetterGrade(91));
        assertEquals("A+", course1.percentageToLetterGrade(90));
        assertEquals("A", course1.percentageToLetterGrade(89));
        assertEquals("A", course1.percentageToLetterGrade(86));
        assertEquals("A", course1.percentageToLetterGrade(85));
        assertEquals("A-", course1.percentageToLetterGrade(84));
        assertEquals("A-", course1.percentageToLetterGrade(81));
        assertEquals("A-", course1.percentageToLetterGrade(80));
        assertEquals("B+", course1.percentageToLetterGrade(79));
        assertEquals("B+", course1.percentageToLetterGrade(77));
        assertEquals("B+", course1.percentageToLetterGrade(76));
        assertEquals("B", course1.percentageToLetterGrade(75));
        assertEquals("B", course1.percentageToLetterGrade(73));
        assertEquals("B", course1.percentageToLetterGrade(72));
        assertEquals("B-", course1.percentageToLetterGrade(71));
        assertEquals("B-", course1.percentageToLetterGrade(69));
        assertEquals("B-", course1.percentageToLetterGrade(68));
        assertEquals("C+", course1.percentageToLetterGrade(67));
        assertEquals("C+", course1.percentageToLetterGrade(65));
        assertEquals("C+", course1.percentageToLetterGrade(64));
        assertEquals("C", course1.percentageToLetterGrade(63));
        assertEquals("C", course1.percentageToLetterGrade(61));
        assertEquals("C", course1.percentageToLetterGrade(60));
        assertEquals("C-", course1.percentageToLetterGrade(59));
        assertEquals("C-", course1.percentageToLetterGrade(56));
        assertEquals("C-", course1.percentageToLetterGrade(55));
        assertEquals("D", course1.percentageToLetterGrade(54));
        assertEquals("D", course1.percentageToLetterGrade(51));
        assertEquals("D", course1.percentageToLetterGrade(50));
        assertEquals("F", course1.percentageToLetterGrade(49));
    }
}