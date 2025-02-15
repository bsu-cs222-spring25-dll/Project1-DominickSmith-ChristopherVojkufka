package edu.bsu.cs;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaFetcher {

    public static String fetchJsonData(String articleName) throws IOException {
        URLConnection connection = connectToWikipedia(articleName);
        return JSONParser.readJsonAsString(connection);
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

    public static String getEncodedUrl(String articleName) {
        return "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(articleName, Charset.defaultCharset()) + "&rvprop=timestamp|user&rvlimit=21&redirects";
        //this limit needs to be 21 for project
    }
}
