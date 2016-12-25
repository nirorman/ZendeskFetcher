package com.nirorman.zendesk.utils;


import com.nirorman.zendesk.exceptions.ReadResourceException;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;


/**
 * Created by ormann on 14/12/2016.
 */
public class FileUtil {
    public static String getFileContentFromResource(final String resourcePath) throws ReadResourceException, URISyntaxException {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
        return getStringFromInputStream(inputStream);
    }
    public static String getFileContentFromPath(final String path) throws ReadResourceException, URISyntaxException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new ReadResourceException(e.getMessage());
        }
        return getStringFromInputStream(inputStream);
    }

    private static String getStringFromInputStream(InputStream inputStream)
    {
        String fileString = null;
        try {
            fileString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString;
    }
}
