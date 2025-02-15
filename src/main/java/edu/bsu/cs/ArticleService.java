package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;

public class ArticleService {
    String getRedirectedArticle(String articleName) throws IOException {
        String jsonData = WikipediaFetcher.fetchJsonData(articleName);
        WikipediaRedirectHandler redirectHandler = new WikipediaRedirectHandler();
        redirectHandler.checkRedirection(jsonData);
        return redirectHandler.isRedirected() ? redirectHandler.getRedirectedArticleName() : null;
    }

    JSONArray fetchRevisions(String articleName) throws IOException {
        String jsonData = WikipediaFetcher.fetchJsonData(articleName);
        return JSONParser.extractRevisions(jsonData);
    }
}
