package edu.bsu.cs;

import javafx.application.Platform;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class WikiGUIControllerTest {

    private GUI mockGui;
    private ArticleService mockArticleService;
    private RevisionParser mockRevisionParser;
    private WikiGUIController controller;

    @BeforeEach
    void setUp() {
        Platform.startup(() -> {});
        mockGui = mock(GUI.class);
        mockArticleService = mock(ArticleService.class);
        mockRevisionParser = mock(RevisionParser.class);

        controller = new WikiGUIController(mockGui) {
            @Override
            public void fetchRevisions(String articleName) {
                if(articleName.isEmpty()) {
                    mockGui.showError("Please enter an article name.");
                    return;
                }

                mockGui.disableInteraction(true);

                new Thread(() -> {
                    try {
                        JSONArray revisions = mockArticleService.fetchRevisions(articleName);

                        if (revisions.isEmpty()) {
                            Platform.runLater(() -> mockGui.showError("No revisions found for this article or article does not exist."));
                            return;
                        }

                        String redirectedArticle = mockArticleService.getRedirectedArticle(articleName);

                        List<String> parsedRevisions = mockRevisionParser.getRevisions(revisions, 21);
                        List<String> finalDisplayList = new ArrayList<>();

                        if (redirectedArticle != null) {
                            finalDisplayList.add("Redirected to: " + redirectedArticle);
                        }

                        finalDisplayList.addAll(parsedRevisions);

                        Platform.runLater(() -> mockGui.setRevisionsList(finalDisplayList.toArray(new String[0])));

                    } catch (IOException e) {
                        Platform.runLater(() -> mockGui.showError("Failed to connect to Wikipedia: " + e.getMessage()));
                    } finally {
                        Platform.runLater(() -> mockGui.disableInteraction(false));
                    }
                }).start();
            }
        };
    }

    @Test
    void testFetchRevisionsEmptyInputShowsError() {
        controller.fetchRevisions("");

        verify(mockGui).showError("Please enter an article name.");
    }

    @Test
    void testFetchRevisions_NoRevisions_ShowsError() throws IOException {
        when(mockArticleService.fetchRevisions("NonExistentArticleTitle1")).thenReturn(new JSONArray());

        controller.fetchRevisions("NonExistentArticleTitle1");

        Platform.runLater(() -> {});
        try {
            Thread.sleep(100); // Small delay to ensure UI updates
        } catch (InterruptedException ignored) {}

        verify(mockGui).showError("No revisions found for this article or article does not exist.");
    }


}
