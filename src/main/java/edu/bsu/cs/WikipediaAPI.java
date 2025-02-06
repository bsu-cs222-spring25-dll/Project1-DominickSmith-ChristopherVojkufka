package edu.bsu.cs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.net.URLEncoder;

public class WikipediaAPI {

    //https://en.wikipedia.org/wiki/Rick_Astley
    //Article Name: Rick Astley
    public static void main() throws IOException {
        URLConnection connection = connectToWikipedia(articleName);
        String jsonData = readJsonAsString(connection);
        printJSON(jsonData);
    }

    private static URLConnection connectToWikipedia(String articleName) throws Exception {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + URLEncoder.encode(articleName, Charset.defaultCharset()) +
                "&rvprop=timestamp|user&rvlimit=4&redirects";
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (christopher.vojkufka@bsu.edu)");
        connection.connect();
        return connection;
    }

    public static String readJsonAsString(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private static void printJSON(String jsonData) {
        System.out.print(jsonData);
    }

    public String fetchWikipediaRevisions(String exampleArticle) {
        return null;
        //put this in testAPI
    }
}
