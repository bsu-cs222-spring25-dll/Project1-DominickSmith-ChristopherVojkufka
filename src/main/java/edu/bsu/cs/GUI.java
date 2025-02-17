package edu.bsu.cs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage window;
    private BorderPane layout;
    private TextField articleInput;
    private Button searchButton;
    private ListView<String> revisionsList;

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

    }
}
