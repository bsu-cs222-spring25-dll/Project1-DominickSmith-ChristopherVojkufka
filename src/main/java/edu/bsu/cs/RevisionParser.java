package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray user = JsonPath.read(testDataStream, "$..user");
        return user.getFirst().toString();
    }

    public List<String> getRevisions(JSONArray revisions, int revisionsToDisplay) {
        List<String> revisionList = new ArrayList<>();
        int count = 1;

        for (int i = revisions.size() - 1; i >= revisions.size() - revisionsToDisplay; i--) {
            String time = JsonPath.read(revisions.get(i), "$.timestamp");
            String user = JsonPath.read(revisions.get(i), "$.user");

            if (time == null) {
                time = "N/A";
            }

            if (user == null) {
                user = "Unknown";
            }

            revisionList.add(String.format("%d  %s  %s", count, time, user));
            count++;
        }
        return revisionList;
    }
}