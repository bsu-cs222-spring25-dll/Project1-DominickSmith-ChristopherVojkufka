package edu.bsu.cs;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JSONParser {
    public static String readJsonAsString(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }
}
