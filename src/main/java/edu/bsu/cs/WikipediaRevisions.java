package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;

import static edu.bsu.cs.JSONParser.extractRevisions;

public class WikipediaRevisions {

    private final WikipediaRedirectHandler redirectHandler = new WikipediaRedirectHandler();
    private String redirectedArticle = null;

    public JSONArray fetchWikipediaRevisions(String articleName) throws IOException {
        try {
            String jsonData = WikipediaFetcher.fetchJsonData(articleName);

            if(articleNameDoesNotExist(jsonData)) {
                throw new IOException("Error: The Wikipedia article \"" + articleName + "\" does not exist");
            }

            JSONArray revisions = extractRevisions(jsonData);
            redirectHandler.checkRedirection(jsonData);

            if(redirectHandler.isRedirected()) {
                redirectedArticle = redirectHandler.getRedirectedArticleName();
            }

            return revisions;
        } catch (IOException e) {
            throw new IOException("Failed to fetch data from Wikipedia API: " + e.getMessage());
        }
    }

    public String getRedirectedArticle() {
        return redirectedArticle;
    }

    private boolean articleNameDoesNotExist(String jsonData) {
        return jsonData.contains("\"missing\""); //detects if article is missing
    }

    }
