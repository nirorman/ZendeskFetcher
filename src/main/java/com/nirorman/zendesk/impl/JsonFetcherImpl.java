package com.nirorman.zendesk.impl;

import com.nirorman.zendesk.api.JsonFetcher;
import com.nirorman.zendesk.exceptions.FetchJsonException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * Created by ormann on 13/12/2016.
 *
 */
public class JsonFetcherImpl implements JsonFetcher {
    private String url;

    public JsonFetcherImpl(String url){
        this.url = url;
    }

    @Override
    public JSONObject fetchJson() throws Exception{
        InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonText);
        } catch (MalformedURLException e) {
            throw new FetchJsonException("Malformed URL exception: " + e.getMessage());
        } catch (IOException e) {
            throw new FetchJsonException(e.getMessage());
        } catch (ParseException e) {
            throw new FetchJsonException("JSON parse exception");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
