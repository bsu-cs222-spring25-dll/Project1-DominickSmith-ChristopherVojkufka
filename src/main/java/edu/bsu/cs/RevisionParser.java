package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class RevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray user = (JSONArray) JsonPath.read(testDataStream, "$..user");
        return user.get(0).toString();
    }
}