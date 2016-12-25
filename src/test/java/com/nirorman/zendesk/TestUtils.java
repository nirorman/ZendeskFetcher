package com.nirorman.zendesk;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import static com.nirorman.zendesk.utils.FileNamesUtil.CATEGORIES_JSON;

/**
 * Created by ormann on 14/12/2016.
 */
 class TestUtils {

    static JsonNode mockCategoriesJson() {
        JsonNode node = null;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(TestUtils.class.
                getResource(CATEGORIES_JSON).getPath());
        try {
            node = objectMapper.readValue(file, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }
}
