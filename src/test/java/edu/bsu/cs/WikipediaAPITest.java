package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaAPITest {

    WikipediaAPI api = new WikipediaAPI();


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
    void testFetchWikipediaRevisionsExist() throws Exception {
        JSONArray revisions = api.fetchWikipediaRevisions("Rick Astley");
        assertNotNull(revisions);
        assertFalse(revisions.isEmpty());
    }

    @Test
    void testFetchWikipediaRevisionsNonExistent() {
        IOException exception = assertThrows(IOException.class, () -> {
            api.fetchWikipediaRevisions("NonExistentArticle123456");
        });

        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    void testRedirectHandling() {
        WikipediaRedirectHandler redirectHandler = new WikipediaRedirectHandler();

        String jsonData = "";
    }

}

