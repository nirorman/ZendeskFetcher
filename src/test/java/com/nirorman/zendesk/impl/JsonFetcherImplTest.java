package com.nirorman.zendesk.impl;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static com.nirorman.zendesk.utils.FileNamesUtil.*;

/**
 * Created by ormann on 14/12/2016.
 */
public class JsonFetcherImplTest {
    private JSONObject response;
    @Before
    public void setUp() throws Exception{
        String url = String.format(URL_TEMPLATE, ACOUNT_HOST, CATEGORIES);
        JsonFetcherImpl fetcher = new JsonFetcherImpl(url);
        response = fetcher.fetchJson();
    }
    @Test
    public void fetchJson() throws Exception {
        String newJsonString = response.toJSONString();
        String originalJsonString = TestUtils.mockCategoriesJson().toJSONString();
        assert(newJsonString.equals(originalJsonString));
    }

}