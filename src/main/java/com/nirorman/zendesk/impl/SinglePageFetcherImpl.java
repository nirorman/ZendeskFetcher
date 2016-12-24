package main.java.com.nirorman.zendesk.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.nirorman.zendesk.api.SinglePageFetcher;

import java.io.*;
import java.net.URL;


/**
 * Created by ormann on 13/12/2016.
 *
 */
public class SinglePageFetcherImpl implements SinglePageFetcher {

    @Override
    public JsonNode fetchSinglePage(String url) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new URL(url).openStream();
        JsonNode node = objectMapper.readValue(inputStream, JsonNode.class);
        if (inputStream != null) {
            inputStream.close();
        }
        return node;
        }
    }
