package main.java.com.nirorman.zendesk.utils;

/**
 * Created by ormann on 14/12/2016.
 */
public class FileNamesUtil {
    public static final String LOCAL_DIR = "C:/temp";
    public static final String CATEGORIES_JSON = "/categories.json";
    public static final String TARGET_FILE_NAME = "CategoryLinks.html";
    public static final String ACCOUNT_HOST = "smartling.zendesk.com";
    public static final String CATEGORIES = "categories";
    public static final String URL_TEMPLATE = "https://%s/api/v2/help_center/%s.json%s";
    public static final String SORT_RESULTS = "?sort_by=updated_at&sort_order=asc";// reduces pagination risks
    public static final String TEMPLATE_FILE_NAME = "template.html";
    public static final String SEPARATOR = "/";
    public static final String NEXT_PAGE ="next_page";
    public static final String USAGE_MESSAGE = "Usage: \n" +
            "1. the base Zendesk account host (e.g. smartling.zendesk.com). \n" +
            "2. the target local directory on the computer running the program (e.g. c:\\temp)\n";
}

