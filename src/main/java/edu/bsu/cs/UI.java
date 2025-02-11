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
        int maxRevisions = Math.min(revisions.size(), 21);

        for (int i = revisions.size() - 1; i >= revisions.size() - maxRevisions; i--) {
            String time = JsonPath.read(revisions.get(i), "$..timestamp").toString().replaceAll("[\\[\\]\"]", "");
            String user = JsonPath.read(revisions.get(i), "$..user").toString().replaceAll("[\\[\\]\"]", "");
            System.out.print(count + " " + time + " " + user);
            count++;
        }
    }

    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        return scanner.nextLine(); //allows multi-word article names
    }
}
