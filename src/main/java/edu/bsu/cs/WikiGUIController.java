package edu.bsu.cs;

import javafx.application.Platform;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WikiGUIController {

    private final ArticleService articleService = new ArticleService();
    private final RevisionParser revisionParser = new RevisionParser();
    private final GUI gui;

    public WikiGUIController(GUI gui) {
        this.gui = gui;
    }

    public void fetchRevisions(String articleName) {

        if(articleName.isEmpty()) {
            gui.showError("Please enter an article name.");
            return;
        }

        gui.disableInteraction(true);

        new Thread(() -> {
            try {
                JSONArray revisions = articleService.fetchRevisions(articleName);

                if (revisions.isEmpty()) {
                    Platform.runLater(() -> gui.showError("No revisions found for this article or article does not exist."));
                    return;
                }

                String redirectedArticle = articleService.getRedirectedArticle(articleName);

                List<String> parsedRevisions = revisionParser.getRevisions(revisions, 21);
                List<String> finalDisplayList = new ArrayList<>();

                if (redirectedArticle != null) {
                    finalDisplayList.add("Redirected to: " + redirectedArticle);
                }

                finalDisplayList.addAll(parsedRevisions);

                Platform.runLater(() -> gui.setRevisionsList(finalDisplayList.toArray(new String[0])));

            } catch (IOException e) {
                Platform.runLater(() -> gui.showError("Failed to connect to Wikipedia: " + e.getMessage()));
            } finally {
                Platform.runLater(() -> gui.disableInteraction(false));
            }
        }).start();
    }
}
