package com.nirorman.zendesk;

import com.fasterxml.jackson.databind.JsonNode;
import com.nirorman.zendesk.api.SinglePageFetcherService;
import com.nirorman.zendesk.impl.SinglePageFetcherServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.nirorman.zendesk.utils.FileNamesUtil.*;

/**
 * Created by ormann on 14/12/2016.
 * This tests the case where no pagination is needed, the whole JSON is returned in one page
 * (The Categories JSON has less than 30 entries)
 */

public class SinglePageFetcherServiceImplTest {
    private JsonNode response;

    @Before
    public void setUp() throws Exception{
        String url = String.format(URL_TEMPLATE, ACCOUNT_HOST, CATEGORIES, SORT_RESULTS, "");
        SinglePageFetcherService fetcher = new SinglePageFetcherServiceImpl();
        response = fetcher.fetchSinglePage(url);
    }

    @Test
    public void fetchJson() throws Exception {
        JsonNode newJson = response.get("categories");
        JsonNode originalJson = TestUtils.mockCategoriesJson().get("categories");
        Assert.assertTrue(newJson.size() == originalJson.size());
        for(int i =0; i< newJson.size(); i++){
            boolean isEqual = newJson.get(i).get("html_url").equals(originalJson.get(i).get("html_url"));
            Assert.assertTrue(isEqual);
        }
    }

    @Test
    public void assertNoMorePagesFound(){
        assert(response.get("next_page").toString().equals("null"));
    }
}