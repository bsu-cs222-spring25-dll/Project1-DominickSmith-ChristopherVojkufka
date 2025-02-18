package edu.bsu.cs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    private Stage window;
    private BorderPane layout;
    private TextField articleInput;
    private Button searchButton;
    private ListView<String> revisionsList;

    private final ArticleService articleService = new ArticleService();
    private final RevisionParser revisionParser = new RevisionParser();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Wikipedia Revision Tracker");

        //input field and search button
        articleInput = new TextField();
        articleInput.setPromptText("Enter Wikipedia Article Name...");

        searchButton = new Button("Search");
        searchButton.setOnAction(e -> fetchRevisions());

        //initialize list view
        revisionsList = new ListView<>();

        //layout
        VBox inputLayout = new VBox(10, articleInput, searchButton);
        inputLayout.setPadding(new Insets(10));

        layout = new BorderPane();
        layout.setTop(inputLayout);
        layout.setCenter(revisionsList);

        //scene builder
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }

    private void fetchRevisions() {
        String articleName = articleInput.getText().trim();

        if(articleName.isEmpty()) {
            showError("Please enter an article name.");
            return;
        }

        disableInteraction(true);

        new Thread(() -> {
            try {
                JSONArray revisions = articleService.fetchRevisions(articleName);

                if (revisions.isEmpty()) {
                    Platform.runLater(() -> showError("No revisions found for this article or article does not exist."));
                    return;
                }

                String redirectedArticle = articleService.getRedirectedArticle(articleName);

                List<String> parsedRevisions = revisionParser.getRevisions(revisions, 21);
                List<String> finalDisplayList = new ArrayList<>();

                if (redirectedArticle != null) {
                    finalDisplayList.add("Redirected to: " + redirectedArticle);
                }

                finalDisplayList.addAll(parsedRevisions);

                Platform.runLater(() -> revisionsList.getItems().setAll(finalDisplayList));

            } catch (IOException e) {
                Platform.runLater(() -> showError("Failed to connect to Wikipedia: " + e.getMessage()));
            } finally {
                Platform.runLater(() -> disableInteraction(false));
            }
        }).start();
    }


    private void disableInteraction(boolean disabledStatus) {
        articleInput.setDisable(disabledStatus);
        searchButton.setDisable(disabledStatus);

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
