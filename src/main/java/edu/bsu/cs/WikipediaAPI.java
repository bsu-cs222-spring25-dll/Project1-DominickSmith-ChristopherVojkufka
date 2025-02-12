package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.net.URLEncoder;

import static edu.bsu.cs.JSONParser.extractRevisions;
import static edu.bsu.cs.JSONParser.readJsonAsString;

public class WikipediaAPI {

    private final WikipediaRedirectHandler redirectHandler = new WikipediaRedirectHandler();

    public JSONArray fetchWikipediaRevisions(String articleName) throws IOException {
        try {
            URLConnection connection = connectToWikipedia(articleName);
            String jsonData = readJsonAsString(connection);

            if(articleNameDoesNotExist(jsonData)) {
                throw new IOException("Error: The Wikipedia article \"" + articleName + "\" does not exist");
            }

            JSONArray revisions = extractRevisions(jsonData);

            redirectHandler.checkRedirection(jsonData);

            return revisions;
        } catch (IOException e) {
            throw new IOException("Failed to fetch data from Wikipedia API: " + e.getMessage());
        }
    }

    private boolean articleNameDoesNotExist(String jsonData) {
        return jsonData.contains("\"missing\""); //detects if article is missing
    }

    public static String getEncodedUrl(String articleName) {
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(articleName, Charset.defaultCharset()) + "&rvprop=timestamp|user&rvlimit=21&redirects";
        //this limit needs to be 21 for project
    }

    private static URLConnection connectToWikipedia(String articleName) throws IOException {
        String encodedUrlString = getEncodedUrl(articleName);
        @SuppressWarnings("deprecation")
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (christopher.vojkufka@bsu.edu)");
        connection.connect();
        return connection;
    }
    }
