package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray user = JsonPath.read(testDataStream, "$..user");
        return user.getFirst().toString();
    }

    public List<String> getRevisions(Object o) {
        return null;
    }
}