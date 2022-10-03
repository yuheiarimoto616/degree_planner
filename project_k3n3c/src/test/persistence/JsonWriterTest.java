package persistence;

import model.Course;
import model.DegreePlanner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvaridFile() {
        try {
            DegreePlanner dp = new DegreePlanner();
            JsonWriter writer = new JsonWriter("./data.\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDegreePlanner() {
        try {
            DegreePlanner dp = new DegreePlanner();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDegreePlanner.json");
            writer.open();
            writer.write(dp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDegreePlanner.json");
            dp = reader.read();
            assertEquals(0, dp.getListOfCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralDegreePlanner() {
        try {
            DegreePlanner dp = new DegreePlanner();
            dp.addCourse(new Course("CPSC", 210, 4, 1, -1));
            dp.addCourse(new Course("WRDS", 150, 3, 2, -1));
            Course cpsc110 = new Course("CPSC", 110, 4, 0, -1);
            cpsc110.setGrade(97);
            dp.addCourse(cpsc110);
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralDegreePlanner.json");
            jsonWriter.open();
            jsonWriter.write(dp);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterGeneralDegreePlanner.json");
            dp = jsonReader.read();
            List<Course> courses = dp.getListOfCourses();
            assertEquals(3, courses.size());
            checkCourse(4, 210, -1, "CPSC", 1, courses.get(0));
            checkCourse(3, 150, -1, "WRDS", 2, courses.get(1));
            checkCourse(4, 110, 97, "CPSC", 0, courses.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
