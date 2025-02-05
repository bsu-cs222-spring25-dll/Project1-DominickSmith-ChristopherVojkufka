package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonFileTest {

    @Test
    void testJsonAccess() {
        String jsonData = readSampleJsonAsString();
        assertTrue(jsonData != null);
    }

    private String readSampleJsonAsString() {
        try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("sample.json")) {
            return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
        } catch (IOException | NullPointerException e) {
            System.err.print("File not found.");
        }
        return null;
    }

}
