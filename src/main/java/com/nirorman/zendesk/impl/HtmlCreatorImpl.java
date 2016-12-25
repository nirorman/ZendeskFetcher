package com.nirorman.zendesk.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.nirorman.zendesk.api.Category;
import com.nirorman.zendesk.api.HtmlCreator;
import com.nirorman.zendesk.exceptions.CreateHtmlFileException;
import com.nirorman.zendesk.utils.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nirorman.zendesk.utils.FileNamesUtil.*;


/**
 * Created by ormann on 13/12/2016.
 *
 *
 */
public class HtmlCreatorImpl implements HtmlCreator {
    private String htmlTargetFilePath;
    private StringBuilder bodyBuilder;
    private List<String> propertyNames;

    public HtmlCreatorImpl(String targetDirPath){
        this.htmlTargetFilePath = targetDirPath + SEPARATOR + TARGET_FILE_NAME;
        this.bodyBuilder = new StringBuilder();
        this.propertyNames = Arrays.asList("name", "html_url");
    }

    public void addJsonNodeToHtmlBody(JsonNode jsonNode){
        ArrayList<Category> categories = getSectionListFromJson(jsonNode, propertyNames);
        addContentToHtmlBody(categories);
    }

    /* Assuming that every category has html_url and name fields*/
    public void createHtmlFile() throws CreateHtmlFileException {
        String body = bodyBuilder.toString();
        try {
            String htmlString = FileUtil.getFileContentFromResource(TEMPLATE_FILE_NAME);
            htmlString = htmlString.replace("$body", body);
            Files.write(Paths.get(htmlTargetFilePath), htmlString.getBytes());
        } catch (IOException e) {
            throw new CreateHtmlFileException(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlTargetFilePath()
    {
        return htmlTargetFilePath;
    }

    private ArrayList<Category> getSectionListFromJson(JsonNode jsonNode, List<String> sections){
        ArrayList<Category> categoryList = new ArrayList<>();
        JsonNode categoriesList = jsonNode.get(CATEGORIES);
        for (JsonNode node : categoriesList){
            Category c = new CategoryImpl();
            for(String s: sections) {
                c.setField(s, node.get(s).asText());
            }
            categoryList.add(c);
        }
        return categoryList;
    }

    private void addContentToHtmlBody(ArrayList<Category> categories) {
        for (Category c : categories) {
            bodyBuilder.append("<li><a href=\"");
            bodyBuilder.append(c.getHtmlUrl());
            bodyBuilder.append("\">");
            bodyBuilder.append(c.getName());
            bodyBuilder.append("</a></li>\n");
        }
    }

}
