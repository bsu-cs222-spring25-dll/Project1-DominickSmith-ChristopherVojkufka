package edu.bsu.cs;

public class ArticleValidator {
    public static void validate(String articleName) {
        if (articleName == null || articleName.isEmpty()) {
            throw new IllegalArgumentException("Error: No article name provided or article name does not exist.");
        }
    }
}
