package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    WikipediaAPI api = new WikipediaAPI();
    private final RevisionPrinter printer = new RevisionPrinter();

    public void startProgram(){
        try {
        String articleName = getArticleName();
        ArticleValidator.validate(articleName);

        JSONArray revisions = api.fetchWikipediaRevisions(articleName);
        checkRevisionsExist(revisions);

        String redirectedArticle = api.getRedirectedArticle();
        if(redirectedArticle != null) {
            System.out.println("Redirected to " + redirectedArticle);
        }

        printer.print(revisions);
    } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: No article name provided.");
        }
    }

    private void checkRevisionsExist(JSONArray revisions) {
        if (revisions == null || revisions.isEmpty()) {
            System.err.print("Error: No revisions found or exist.");
            System.exit(1);
        }
    }

    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
