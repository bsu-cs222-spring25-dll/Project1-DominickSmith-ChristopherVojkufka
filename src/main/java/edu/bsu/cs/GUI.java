package edu.bsu.cs;

import javafx.application.Application;
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

        try {
            JSONArray revisions = articleService.fetchRevisions(articleName);

            if(revisions.isEmpty()) {
                showError("No revisions found for this article.");
                return;
            }

            List<String> parsedRevisions = revisionParser.getRevisions(revisions, 21);
            revisionsList.getItems().setAll(parsedRevisions);
        } catch (IOException e) {
            showError("Failed to fetch data: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
