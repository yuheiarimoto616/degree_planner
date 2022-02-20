package persistence;

import model.Course;
import model.DegreePlanner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReadingNonExistenceFile() {
        JsonReader reader = new JsonReader(".data/non.json");
        try {
            DegreePlanner dp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadingEmptyDegreePlanner() {
        JsonReader reader = new JsonReader("./data/testReadingEmptyDegreePlanner.json");
        try {
            DegreePlanner dp = reader.read();
            assertEquals(0, dp.getListOfCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadingGeneralDegreePlanner() {
        JsonReader reader = new JsonReader("./data/testReadingGeneralDegreePlanner.json");
        try {
            DegreePlanner dp = reader.read();
            List<Course> courses = dp.getListOfCourses();
            assertEquals(3, courses.size());
            checkCourse(4, 210, -1, "CPSC", 1, courses.get(0));
            checkCourse(4, 110, 97, "CPSC", 0, courses.get(1));
            checkCourse(3, 150, -1, "WRDS", 2, courses.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
