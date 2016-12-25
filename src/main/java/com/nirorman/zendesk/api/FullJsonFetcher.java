package main.java.com.nirorman.zendesk.api;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Created by ormann on 21/12/2016.
 */
public interface FullJsonFetcher
{
    void fetch(String pagination);

    void fetch();

    SinglePageFetcherService getSinglePageFetcherService();

    void setSinglePageFetcherService(SinglePageFetcherService singlePageFetcherService);

    List<JsonNode> getJsonNodes();

    void setJsonNodes(List<JsonNode> nodes);
}
