package com.nirorman.zendesk.api;

import org.json.simple.JSONObject;

/**
 * Created by ormann on 14/12/2016.
 */
public interface JsonFetcher {
    JSONObject fetchJson() throws Exception;
}
