package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

public class WikipediaRedirectHandler {

    private boolean isRedirected = false;
    private String redirectedArticleName = "";

    public void checkRedirection(String jsonData) {
        if(jsonData.contains("redirects")) {
            redirectedArticleName = JsonPath.read(jsonData, "$.query.redirects[0].to").toString();
            isRedirected = true;
        }
    }

    public boolean isRedirected() {
        return isRedirected;
    }

    public String getRedirectedArticleName() {
        return redirectedArticleName;
    }
}
