package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JSONParser {
    public static String readJsonAsString(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    public static JSONArray extractRevisions(String jsonData) {
        return JsonPath.read(jsonData, "$..revisions[*]");
    }
}
