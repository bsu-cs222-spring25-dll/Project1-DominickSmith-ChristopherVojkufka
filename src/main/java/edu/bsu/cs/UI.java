package edu.bsu.cs;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    WikipediaAPI api = new WikipediaAPI();

    public void start () throws Exception {
        String articleName = getArticleName();
        api.fetchWikipediaRevisions(articleName);
    }

    public String getArticleName(){
        System.out.println("Please enter the name of a Wikipedia article: ");
        String input = scanner.next();
        return input;
    }
}
