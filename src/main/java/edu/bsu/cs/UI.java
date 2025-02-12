package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    WikipediaAPI api = new WikipediaAPI();

    public void startProgram() throws IOException {
        try {
        String articleName = getArticleName();
        checkValidArticle(articleName);

        JSONArray revisions = api.fetchWikipediaRevisions(articleName);
        checkRevisionsExist(revisions);

        checkArticleRedirection();
        printRevisions(revisions);
    } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



    private void checkValidArticle(String articleName) {
        if (articleName == null || articleName.isEmpty()) {
            System.err.println("Error: No article name provided or article name does not exist.");
            System.exit(1); //Exit if no name is provided
        }
    }

    private void checkRevisionsExist(JSONArray revisions) {
        if (revisions == null || revisions.isEmpty()) {
            System.err.print("Error: No revisions found or exist.");
            System.exit(1);
        }
    }

    private void printRevisions(JSONArray revisions) {
        RevisionParser revisionParser = new RevisionParser();

        checkArticleRedirection();

        int totalRevisions = revisions.size();
        int revisionsToDisplay = Math.min(totalRevisions, 21);

        List<String> parsedRevisions = revisionParser.getRevisions(revisions, revisionsToDisplay);
        for (String revision : parsedRevisions) {
            System.out.println(revision); // Prints each revision on a new line
        }
    }

    private void checkArticleRedirection() {
        WikipediaRedirectHandler redirectHandler = new WikipediaRedirectHandler();

        if(redirectHandler.isRedirected()) {
            System.out.println("Redirected to: " + redirectHandler.getRedirectedArticleName());
        }
    }


    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
