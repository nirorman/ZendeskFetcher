package com.nirorman.zendesk.impl;

import org.apache.commons.lang3.builder.EqualsBuilder;
import com.nirorman.zendesk.api.HtmlCreator;
import com.nirorman.zendesk.utils.ResourceUtil;
import junit.framework.TestCase;
import org.json.simple.JSONObject;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;


import static com.nirorman.zendesk.utils.FileNamesUtil.LOCAL_DIR;
import static com.nirorman.zendesk.utils.FileNamesUtil.TARGET_FILE_NAME;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertThat;


/**
 * Created by ormann on 13/12/2016.
 */
public class HtmlCreatorTest {
    private HtmlCreator htmlCreator;
    private File createdFile;


    @Before
    public void setUp() throws Exception{
        JSONObject jsonObject = TestUtils.mockCategoriesJson();
        htmlCreator = new HtmlCreatorImpl(LOCAL_DIR);
        htmlCreator.createHtmlFile(jsonObject);
        createdFile = new File (htmlCreator.getHtmlTargetFilePath());
    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        Assert.assertTrue(createdFile.delete());
    }

    @Test
    public void testCreateHtmlFile() throws Exception {
        Assert.assertTrue(createdFile.exists());
    }

    @Test
    public void testHtmlFileContent()throws Exception{
        String exampleFileString = ResourceUtil.getFileContent(TARGET_FILE_NAME);
        String createdFileString = FileUtils.readFileToString(createdFile);
        Assert.assertEquals(exampleFileString, createdFileString);
    }

}