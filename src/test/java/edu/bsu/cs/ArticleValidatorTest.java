package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleValidatorTest {

    @Test
    void testValidArticleName() {
        assertDoesNotThrow(() -> ArticleValidator.validate("validArticle"));
    }

    @Test
    void testInvalidArticleName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ArticleValidator.validate(""));
        assertEquals("Error: No article name provided or article name does not exist.", exception.getMessage());
    }
}
