package com.nirorman.zendesk;

import com.nirorman.zendesk.exceptions.CreateHtmlFileException;
import com.nirorman.zendesk.exceptions.FetchJsonException;
import com.nirorman.zendesk.impl.HtmlCreatorImpl;
import com.nirorman.zendesk.impl.JsonFetcherImpl;
import org.json.simple.JSONObject;

import static com.nirorman.zendesk.utils.FileNamesUtil.CATEGORIES;
import static com.nirorman.zendesk.utils.FileNamesUtil.URL_TEMPLATE;

public class Main {
    private static final String USAGE_MESSAGE = "Usage: \n" +
            "1. the base Zendesk account host (e.g. smartling.zendesk.com). \n" +
            "2. the target local directory on the computer running the program (e.g. c:\\temp)\n";


    public static void main(String[] args) throws Exception{
        if (args.length != 2){
            System.out.println(USAGE_MESSAGE);
            return;
        }
        final String accountHost = args[0];
        final String targetDirPath = args[1];

        try {
            String url = String.format(URL_TEMPLATE, accountHost, CATEGORIES);
            JsonFetcherImpl fetcher = new JsonFetcherImpl(url);
            JSONObject response = fetcher.fetchJson();

            HtmlCreatorImpl htmlCreator = new HtmlCreatorImpl(targetDirPath);
            htmlCreator.createHtmlFile(response);
        }
        catch (FetchJsonException e ){
            System.out.println(String.format("Failed to fetch Json: %s" , e.getMessage()));
        }
        catch(CreateHtmlFileException e)
        {
            System.out.println(String.format("failed to read HTML file: %s" , e.getMessage()));
        }
        catch (Exception e) {
            System.out.println(String.format("Unexpected error: %s" , e.getMessage()));
        }
    }
}
