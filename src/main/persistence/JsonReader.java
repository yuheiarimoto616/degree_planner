package persistence;

import model.Course;
import model.DegreePlanner;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads degree planner from JSON data stored in file
// Citation: JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads degree planner from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public DegreePlanner read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDegreePlanner(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it;
    //          throws IOException if an error occurs reading file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses degree planner from JSON object and returns it
    private DegreePlanner parseDegreePlanner(JSONObject jsonObject) {
        DegreePlanner degreePlanner = new DegreePlanner();
        addCourses(degreePlanner, jsonObject);
        return degreePlanner;
    }

    // MODIFIES: degreePlanner
    // EFFECTS: parses courses from JSON object and adds them to degree planner
    private void addCourses(DegreePlanner degreePlanner, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject course = (JSONObject) json;
            addCourse(degreePlanner, course);
        }
    }

    // MODIFIES: degreePlanner
    // EFFECTS: parses course from JSON object and adds it to degree planner
    private void addCourse(DegreePlanner degreePlanner, JSONObject jsonObject) {
        String subjectCode = jsonObject.getString("subjectCode");
        int courseCode = jsonObject.getInt("courseCode");
        int grade = jsonObject.getInt("grade");
        int status = jsonObject.getInt("status");
        int credits = jsonObject.getInt("credits");

        Course course = new Course(subjectCode, courseCode, credits, status, grade);
        degreePlanner.addCourse(course);
    }
}
