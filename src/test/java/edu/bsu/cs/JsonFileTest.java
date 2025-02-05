package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonFileTest {

    @Test
    void testJsonAccess() {
        String jsonData = readSampleJsonAsString();
        assertTrue(jsonData != null);
    }
}
