package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class RevisionParserTest {

    @Test
    public void testParse() throws IOException {
        RevisionParser parser = new RevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData.json");
        String user = parser.parse(testDataStream);
        Assertions.assertEquals("Chiswick Chap", user);
    }
}
