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

    public JSONArray fetchWikipediaRevisions(String articleName) throws IOException {
        URLConnection connection = connectToWikipedia(articleName);
        String jsonData = readJsonAsString(connection);
        return extractRevisions(jsonData);
    }


    private static URLConnection connectToWikipedia(String articleName) throws IOException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(articleName, Charset.defaultCharset()) + "&rvprop=timestamp|user&rvlimit=4&redirects";
        @SuppressWarnings("deprecation")
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (christopher.vojkufka@bsu.edu)");
        connection.connect();
        return connection;
    }


}
