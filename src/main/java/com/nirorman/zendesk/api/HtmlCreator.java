package com.nirorman.zendesk.api;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by ormann on 14/12/2016.
 */
public interface HtmlCreator {

    void createHtmlFile() throws Exception;

    String getHtmlTargetFilePath();

    void addJsonNodeToHtmlBody(JsonNode jsonNode);
}
