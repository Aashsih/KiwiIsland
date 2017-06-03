/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class GameHelpTest extends junit.framework.TestCase 
{
    private static final String ENCODING = "UTF-8";
    private StringBuilder gameHelpStringBuffer;
    /**
     * Default constructor for the class GameHelpTest
     */
    public GameHelpTest()
    {
        //Default constructor for the test class
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        try {
            gameHelpStringBuffer = new StringBuilder();
            gameHelpStringBuffer.append(IOUtils.toString(ScoreBoard.class.getResourceAsStream(TextFilePathConstants.HELP), ENCODING));
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        try { 
            FileUtils.writeStringToFile(new File(GameHelp.class.getResource(TextFilePathConstants.HELP).getFile()), gameHelpStringBuffer.toString(), ENCODING);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetGameHelpInfoMatchesTextInFile()
    {
        try 
        {
            String testData = "TestData\nSome more test data\n And some more test data";
            FileUtils.writeStringToFile(new File(GameHelp.class.getResource(TextFilePathConstants.HELP).getFile()), testData , ENCODING);
            String result = GameHelp.getGameHelpInfo();
            assertEquals(testData, result);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
