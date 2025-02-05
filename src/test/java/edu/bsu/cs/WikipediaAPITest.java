package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaAPITest {

    @Test
    void testWikipediaApiIsReachable() throws Exception {
        URL url = new URL("https://en.wikipedia.org/w/api.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "Wikipedia API is not reachable");
    }
}

