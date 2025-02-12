package edu.bsu.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RevisionPrinterTest {

    @Test
    void testPrintWithValidRevisions() {
        RevisionPrinter printer = new RevisionPrinter();
        JSONArray mockRevisions = new JSONArray();
        mockRevisions.add("{\"timestamp\": \"2024-02-10T12:34:56Z\", \"user\": \"TestUser\"}");

        assertDoesNotThrow(() -> printer.print(mockRevisions));
    }
}
