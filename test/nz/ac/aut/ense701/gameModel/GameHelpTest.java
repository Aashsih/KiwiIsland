/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class GameHelpTest extends junit.framework.TestCase 
{
    private StringBuffer gameHelpStringBuffer;
    /**
     * Default constructor for the class GameHelpTest
     */
    public GameHelpTest()
    {
        
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
            gameHelpStringBuffer = new StringBuffer();
            gameHelpStringBuffer.append(FileUtils.readFileToString(new File("Help.txt"), "UTF-8"));
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
            FileUtils.writeStringToFile(new File("Help.txt"), gameHelpStringBuffer.toString() , "UTF-8");
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
            FileUtils.writeStringToFile(new File("Help.txt"), testData , "UTF-8");
            String result = GameHelp.getGameHelpInfo();
            assertEquals(testData, result);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
