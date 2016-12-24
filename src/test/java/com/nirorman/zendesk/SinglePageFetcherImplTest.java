package test.java.com.nirorman.zendesk;

import com.fasterxml.jackson.databind.JsonNode;
import main.java.com.nirorman.zendesk.api.SinglePageFetcher;
import main.java.com.nirorman.zendesk.impl.SinglePageFetcherImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static main.java.com.nirorman.zendesk.utils.FileNamesUtil.*;

/**
 * Created by ormann on 14/12/2016.
 * This tests the case where no pagination is needed, the whole JSON is returned in one page
 * (The Categories JSON has less than 30 entries)
 */

public class SinglePageFetcherImplTest {
    private JsonNode response;

    @Before
    public void setUp() throws Exception{
        String url = String.format(URL_TEMPLATE, ACCOUNT_HOST, CATEGORIES, SORT_RESULTS);
        SinglePageFetcher fetcher = new SinglePageFetcherImpl();
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