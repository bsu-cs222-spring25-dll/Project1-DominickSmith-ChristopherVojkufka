package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.List;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    WikipediaAPI api = new WikipediaAPI();

    public void startProgram() throws Exception {
        String articleName = getArticleName();
        checkValidArticle(articleName);

        JSONArray revisions = api.fetchWikipediaRevisions(articleName);
        checkRevisionsExist(String.valueOf(revisions));

        printRevisions(revisions);
    }

    private void checkValidArticle(String articleName) {
        if (articleName == null || articleName.isEmpty()) {
            System.err.println("Error: No article name provided or article name does not exist.");
            System.exit(1); //Exit if no name is provided
        }
    }

    private void checkRevisionsExist(String revisions) {
        if (revisions == null || revisions.isEmpty()) {
            System.err.print("Error: No revisions found or exist.");
            System.exit(1);
        }
    }

    private void printRevisions(JSONArray revisions) {
        RevisionParser revisionParser = new RevisionParser();

        List<String> parsedRevisions = revisionParser.getRevisions(revisions);
        for (String revision : parsedRevisions) {
            System.out.println(revision); // Prints each revision on a new line
        }
    }


    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
