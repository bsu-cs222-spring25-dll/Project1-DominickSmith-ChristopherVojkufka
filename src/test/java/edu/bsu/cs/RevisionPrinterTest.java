package edu.bsu.cs;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RevisionPrinterTest {

    @Test
    void testPrintWithValidRevisions() {
        RevisionPrinter printer = new RevisionPrinter();

        JSONObject mockRevision = new JSONObject();
        mockRevision.put("timestamp", "2024-02-10T12:34:56Z");
        mockRevision.put("user", "TestUser");

        JSONArray mockRevisions = new JSONArray();
        mockRevisions.add(mockRevision);

        assertDoesNotThrow(() -> printer.print(mockRevisions));
    }
}
