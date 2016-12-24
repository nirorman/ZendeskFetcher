package test.java.com.nirorman.zendesk;

import com.fasterxml.jackson.databind.JsonNode;
import main.java.com.nirorman.zendesk.api.HtmlCreator;
import main.java.com.nirorman.zendesk.impl.HtmlCreatorImpl;
import main.java.com.nirorman.zendesk.utils.FileUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;


import static main.java.com.nirorman.zendesk.utils.FileNamesUtil.LOCAL_DIR;
import static main.java.com.nirorman.zendesk.utils.FileNamesUtil.TARGET_FILE_NAME;



/**
 * Created by ormann on 13/12/2016.
 */
public class HtmlCreatorTest {
    private HtmlCreator htmlCreator;
    private File createdFile;
    private String filePath;


    @Before
    public void setUp() throws Exception{
        JsonNode jsonNode = TestUtils.mockCategoriesJson();
        htmlCreator = new HtmlCreatorImpl(LOCAL_DIR);
        htmlCreator.addJsonNodeToHtmlBody(jsonNode);
        htmlCreator.createHtmlFile();
        filePath = htmlCreator.getHtmlTargetFilePath();
        createdFile = new File (filePath);
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
        String exampleFileString = FileUtil.getFileContentFromResource(TARGET_FILE_NAME).replaceAll("\r","");
        String createdFileString = FileUtil.getFileContentFromPath(filePath).replaceAll("\r","");
        Assert.assertEquals(exampleFileString, createdFileString);

    }

}