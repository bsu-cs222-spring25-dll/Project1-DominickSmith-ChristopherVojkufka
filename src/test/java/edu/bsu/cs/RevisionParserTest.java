package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RevisionParserTest {

    @Test
    public void testParse() throws IOException {
        RevisionParser parser = new RevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData.json");
        String user = parser.parse(testDataStream);
        assertEquals("Chiswick Chap", user);
    }

    @Test
    public void testGetRevisionsCorrectFormat() throws IOException {
        RevisionParser parser = new RevisionParser();

        String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/sample.json")));
        JSONArray sampleRevisions = JsonPath.read(jsonContent, "$.query.pages.*.revisions[*]");

        int totalRevisions = sampleRevisions.size();
        int revisionsToDisplay = Math.min(totalRevisions, 4);

        List<String> revisionList = parser.getRevisions(sampleRevisions, revisionsToDisplay);

        assertEquals(4, revisionList.size());
        assertEquals("1  2023-09-02T15:05:04Z  Freefry", revisionList.get(0));
        assertEquals("2  2023-09-02T15:06:03Z  Freefry", revisionList.get(1));
        assertEquals("3  2023-09-07T17:21:48Z  ModernDayTrilobite", revisionList.get(2));
        assertEquals("4  2023-09-07T18:34:43Z  Miklogfeather", revisionList.get(3));
    }
}
