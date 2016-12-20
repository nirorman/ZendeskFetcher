package com.nirorman.zendesk.impl;

import com.nirorman.zendesk.api.Category;
import com.nirorman.zendesk.api.HtmlCreator;
import com.nirorman.zendesk.exceptions.CreateHtmlFileException;
import com.nirorman.zendesk.exceptions.ReadResourceException;
import com.nirorman.zendesk.utils.ResourceUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nirorman.zendesk.utils.FileNamesUtil.SEPARATOR;
import static com.nirorman.zendesk.utils.FileNamesUtil.TARGET_FILE_NAME;
import static com.nirorman.zendesk.utils.FileNamesUtil.TEMPLATE_FILE_NAME;


/**
 * Created by ormann on 13/12/2016.
 *
 * used : http://stackoverflow.com/questions/5936003/write-html-file-using-java
 */
public class HtmlCreatorImpl implements HtmlCreator {
    private String targetDirPath;
    private String htmlTargetFilePath;


    public HtmlCreatorImpl(String targetDirPath){

        this.targetDirPath = targetDirPath;
        this.htmlTargetFilePath = targetDirPath + SEPARATOR + TARGET_FILE_NAME;
    }

    /* Assuming that every category has html_url and name fields*/
    public void createHtmlFile(JSONObject jsonObject) throws CreateHtmlFileException {
        List<String> propertyNames = Arrays.asList("name", "html_url");
        ArrayList<Category> categories = getSectionListFromJson(jsonObject, propertyNames);

        String body = buildBody(categories);
        try {
            String htmlString = ResourceUtil.getFileContent(TEMPLATE_FILE_NAME);
            htmlString = htmlString.replace("$body", body);
            Files.write(Paths.get(htmlTargetFilePath), htmlString.getBytes());
        } catch (IOException e) {
            throw new CreateHtmlFileException(e.getMessage());
        }
    }

    public String getHtmlTargetFilePath()
    {
        return htmlTargetFilePath;
    }

    private ArrayList<Category> getSectionListFromJson(JSONObject jsonObject, List<String> sections){
        ArrayList<Category> categoryList = new ArrayList<>();
        JSONArray categoriesList = (JSONArray) jsonObject.get("categories");
        for (Object j : categoriesList){
            Category c = new CategoryImpl();
            for(String s: sections) {
                c.setField(s, (String) ((JSONObject) j).get(s));
            }
            categoryList.add(c);
        }
        return categoryList;
    }
    private String buildBody(ArrayList<Category> categories){
        StringBuilder sb = new StringBuilder();
        for(Category c : categories){
            sb.append("<li><a href=\"");
            sb.append(c.getHtmlUrl());
            sb.append("\">");
            sb.append(c.getName());
            sb.append("</a></li>\n");
        }
        return sb.toString();
    }

}
