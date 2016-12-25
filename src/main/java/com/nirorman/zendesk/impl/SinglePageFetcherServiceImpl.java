package main.java.com.nirorman.zendesk.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.nirorman.zendesk.api.SinglePageFetcherService;
import main.java.com.nirorman.zendesk.exceptions.FetchJsonException;
import main.java.com.nirorman.zendesk.exceptions.UrlException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by ormann on 13/12/2016.
 */
public class SinglePageFetcherServiceImpl implements SinglePageFetcherService {

    @Override
    public JsonNode fetchSinglePage(String url) throws UrlException, FetchJsonException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream;
        try {
            inputStream = new URL(url).openStream();
        } catch (Exception e) {
            throw new UrlException(e.getMessage());
        }
        JsonNode node;
        try {
            node = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new FetchJsonException(e.getMessage());
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new FetchJsonException(e.getMessage());
            }
        }
        return node;
    }
}
