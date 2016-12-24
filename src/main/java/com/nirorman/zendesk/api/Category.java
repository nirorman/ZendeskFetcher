package main.java.com.nirorman.zendesk.api;

/**
 * Created by ormann on 14/12/2016.
 */
public interface Category {
    String getName();

    String getHtmlUrl();

    void setField(String field, String value);
}
