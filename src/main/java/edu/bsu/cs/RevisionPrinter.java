package edu.bsu.cs;

import net.minidev.json.JSONArray;

import java.util.List;

public class RevisionPrinter {
    private final RevisionParser revisionParser = new RevisionParser();

    public void print(JSONArray revisions) {
        int revisionsToDisplay = Math.min(revisions.size(), 21);

        List<String> parsedRevisions = revisionParser.getRevisions(revisions, revisionsToDisplay);
        for (String revision : parsedRevisions) {
            System.out.println(revision); // Prints each revision on a new line
        }
    }
}
