package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaAPITest {

    @Test
    void testWikipediaApiIsReachable() throws Exception {
        @SuppressWarnings("deprecation")
        URL url = new URL("https://en.wikipedia.org/w/api.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "Wikipedia API is not reachable");
    }

    @Test
    void testFetchWikipediaRevisions() throws Exception {
        WikipediaAPI api = new WikipediaAPI();
        JSONArray jsonData = api.fetchWikipediaRevisions("Rick Astley");
        assertNotNull(jsonData, "API should return JSON data.");
        assertTrue(jsonData.contains("query"), "JSON should contain 'query' key.");
    }

}

