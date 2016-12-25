package main.java.com.nirorman.zendesk.api;

import com.fasterxml.jackson.databind.JsonNode;
import main.java.com.nirorman.zendesk.exceptions.FetchJsonException;
import main.java.com.nirorman.zendesk.exceptions.UrlException;

/**
 * Created by ormann on 14/12/2016.
 */
public interface SinglePageFetcherService {

    JsonNode fetchSinglePage(String url) throws UrlException, FetchJsonException;

}
