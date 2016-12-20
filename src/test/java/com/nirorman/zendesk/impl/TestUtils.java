package com.nirorman.zendesk.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import static com.nirorman.zendesk.utils.FileNamesUtil.CATEGORIES_JSON;

/**
 * Created by ormann on 14/12/2016.
 */
 class TestUtils {

    static JSONObject mockCategoriesJson() {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(TestUtils.class.
                    getResource(CATEGORIES_JSON).getPath()));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
