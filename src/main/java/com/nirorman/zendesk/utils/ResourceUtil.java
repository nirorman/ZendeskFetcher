package com.nirorman.zendesk.utils;

import com.nirorman.zendesk.exceptions.ReadResourceException;
import java.io.File;
import java.io.InputStream;


/**
 * Created by ormann on 14/12/2016.
 */
public class ResourceUtil {
    public static String getFileContent(final String resourcePath) throws ReadResourceException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
