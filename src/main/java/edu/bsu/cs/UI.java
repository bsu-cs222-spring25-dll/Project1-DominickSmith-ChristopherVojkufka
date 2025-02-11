package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
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

        printRevisions(revisions);
    }

    private void printRevisions(JSONArray revisions) {
        int count = 1;
        for (Object revision : revisions) {
            String time = JsonPath.read(revision, "$.timestamp").toString();
            String user = JsonPath.read(revision, "$.user").toString();
            System.out.print(count + " " + time + " " + user);
            count++;
        }
    }

    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
