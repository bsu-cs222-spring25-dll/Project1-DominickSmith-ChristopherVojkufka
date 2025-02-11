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

    public List<String> getRevisions(JSONArray revisions) {
        List<String> revisionList = new ArrayList<>();
        int count = 1;
        int maxRevisions = Math.min(revisions.size(), 21);

        for (int i = revisions.size() - 1; i >= revisions.size() - maxRevisions; i--) {
            String time = JsonPath.read(revisions.get(i), "$..timestamp").toString();
            String user = JsonPath.read(revisions.get(i), "$..user").toString();

            revisionList.add(count + "  " + time + "  " + user + "\n");
            count++;
        }
        return revisionList;
    }
}