package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    WikipediaAPI api = new WikipediaAPI();

    public void start () throws Exception {
        String articleName = getArticleName();
        if(articleName == null || articleName.isEmpty()) {
            System.err.println("Error: No article name provided.");
            System.exit(1); //Exit if no name is provided
        }

        JSONArray revisions = api.fetchWikipediaRevisions(articleName);
        if(revisions == null || revisions.isEmpty()) {
            System.err.print("Error: No revisions found or invalid article.");
            System.exit(1);
        }

        RevisionParser revisionParser = new RevisionParser();
        revisionParser.getRevisions(revisions);
    }


    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
