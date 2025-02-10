package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonFileTest {

    @Test
    void testJsonAccess() {
       String jsonData = readSampleJsonAsString();
        assertNotNull(jsonData);
    }

    @Test
    void testJsonCountRevisions() {
        String jsonData = readSampleJsonAsString();
        JSONArray articleRevisions = getRevisionsFromJson(jsonData);
        Assertions.assertEquals(4, articleRevisions.size());
    }

    private String readSampleJsonAsString() {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json")) {
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            System.err.print("No input was found.");
        } catch (NullPointerException e) {
            System.err.print("File not found");
        }
        return null;
    }

    private JSONArray getRevisionsFromJson(String jsonData) {
        return JsonPath.read(jsonData, "$..revisions[*]");
    }

}
