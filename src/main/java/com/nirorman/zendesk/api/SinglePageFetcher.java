package main.java.com.nirorman.zendesk.api;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by ormann on 14/12/2016.
 */
public interface SinglePageFetcher {
    JsonNode fetchSinglePage(String url) throws Exception;
}
