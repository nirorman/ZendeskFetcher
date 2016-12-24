package main.java.com.nirorman.zendesk;

import main.java.com.nirorman.zendesk.api.FullJsonFetcher;
import main.java.com.nirorman.zendesk.impl.FullJsonFetcherImpl;
import static main.java.com.nirorman.zendesk.utils.FileNamesUtil.USAGE_MESSAGE;


public class Main {

    public static void main(String[] args) throws Exception{
        if (args.length != 2){
            System.out.println(USAGE_MESSAGE);
            return;
        }
        final String accountHost = args[0];
        final String targetDirPath = args[1];

        FullJsonFetcher fetcher = new FullJsonFetcherImpl(accountHost, targetDirPath);
        fetcher.fetch();
    }
}
