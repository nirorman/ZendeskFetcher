package test.java.com.nirorman.zendesk;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.nirorman.zendesk.api.FullJsonFetcher;
import main.java.com.nirorman.zendesk.api.SinglePageFetcherService;
import main.java.com.nirorman.zendesk.exceptions.FetchJsonException;
import main.java.com.nirorman.zendesk.exceptions.ReadResourceException;
import main.java.com.nirorman.zendesk.exceptions.UrlException;
import main.java.com.nirorman.zendesk.impl.FullJsonFetcherImpl;
import main.java.com.nirorman.zendesk.utils.FileUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.nirorman.zendesk.utils.FileNamesUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by ormann on 22/12/2016.
 * Test for the Full Json Fetcher implementation class by injecting in it a mock of the single page fetcher service.
 */
@RunWith(MockitoJUnitRunner.class)
public class FullJsonFetcherImplTest {
    private static final String PAGINATION = "?per_page=%s";
    private int pageAmount;
    private FullJsonFetcher fullJsonFetcher;
    private String targetFilePath;

    @Mock
    private SinglePageFetcherService singlePageFetcherService;

    @Before
    public void setUp() throws UrlException, FetchJsonException {
        pageAmount = 2;
        //Create a fullJsonFetcher object which is to be mocked:
        fullJsonFetcher = new FullJsonFetcherImpl(TEST_HOST, LOCAL_DIR);

        //Create the mock object of the single page fetcher service:
        singlePageFetcherService = mock(SinglePageFetcherService.class);

        //Set the single page fetcher service to the full json fetcher:
        fullJsonFetcher.setSinglePageFetcherService(singlePageFetcherService);
        targetFilePath = LOCAL_DIR + SEPARATOR + TARGET_FILE_NAME;

        //mock the behavior of the nodes to return the full page:
        List<JsonNode> nodeList = new ArrayList<>();
        String urlList[] = {"url2", "url3", "url4"};
        String firstUrl = "https://nirorman.com/api/v2/help_center/categories.json?sort_by=updated_at&sort_order=asc?per_page=2";
        for (int i = 2; i < 5; i++) {
            nodeList.add(createNode(i, i == 4));
        }
        when(singlePageFetcherService.fetchSinglePage(anyString())).thenAnswer(
                invocationOnMock -> {
                    Object args[] = invocationOnMock.getArguments();
                    assertEquals(1, args.length);
                    String url = (String) args[0];
                    if (firstUrl.equals(url)) {
                        return nodeList.get(0);
                    } else {
                        for (int i = 0; i < 3; i++) {
                            if (urlList[i].equals(url)) {
                                return nodeList.get(i + 1);
                            }
                        }

                        return createNode(1, false);

                    }
                }
        );


    }

    @After
    public void tearDown() throws Exception {
        Assert.assertTrue(new File(targetFilePath).delete());
    }

    @Test
    public void fetch() throws UrlException, FetchJsonException, URISyntaxException, ReadResourceException {

        fullJsonFetcher.fetch(String.format(PAGINATION, pageAmount));
        String exampleFileString = FileUtil.getFileContentFromResource(MOCK_CATEGORIES_FILE_NAME).replaceAll("\r", "");
        String createdFileString = FileUtil.getFileContentFromPath(targetFilePath).replaceAll("\r", "");
        Assert.assertEquals(exampleFileString, createdFileString);
    }

    private JsonNode createNode(int number, boolean isLastPage) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        String urlTemplate = "url%s";
        String nextUrl = isLastPage ? "null" : String.format(urlTemplate, number);
        String jsonString = "{\"categories\":[{\"html_url\":\"value1\",\"name\":\"category1\"" +
                "},{\"html_url\":\"value2\",\"name\":\"category2\"}]," +
                "\"next_page\":\"" + nextUrl + "\"}";
        try {
            actualObj = mapper.readValue(jsonString, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return actualObj;
    }

}