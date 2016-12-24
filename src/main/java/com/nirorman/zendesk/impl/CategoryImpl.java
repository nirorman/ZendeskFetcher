package main.java.com.nirorman.zendesk.impl;

import main.java.com.nirorman.zendesk.api.Category;

/**
 * Created by ormann on 14/12/2016.
 */
public class CategoryImpl implements Category {
    private String name;
    private String htmlUrl;

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getHtmlUrl(){
        return htmlUrl;
    }

    @Override
    public void setField(String field, String value){
        switch(field){
            case "name":
                setName(value);
                break;
            case "html_url":
                setHtmlUrl(value);
                break;
        }
    }

    private void setName(String name){
        this.name = name;
    }

    private void setHtmlUrl(String url){
        this.htmlUrl = url;
    }


}
