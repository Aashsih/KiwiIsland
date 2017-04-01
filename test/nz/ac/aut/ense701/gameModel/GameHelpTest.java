/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
       gameHelpStringBuffer = new StringBuffer();
       BufferedReader gameHelpBufferedReader = null;
       try 
        {
            gameHelpBufferedReader = new BufferedReader(new FileReader("Help.txt"));
            for(String line; (line = gameHelpBufferedReader.readLine()) != null; gameHelpStringBuffer.append(line + "\n"));
            gameHelpBufferedReader.close();
        } catch (FileNotFoundException ex) 
        {
       
        } catch (IOException ex) {
            
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
        try 
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Help.txt"));
            bufferedWriter.write(gameHelpStringBuffer.toString());
            bufferedWriter.close();
        } catch (IOException ex) 
        {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetGameHelpInfoMatchesTextInFile()
    {
        try 
        {
            String testData = "TestData\nSome more test data\n And some more test data";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Help.txt"));
            bufferedWriter.write(testData);
            bufferedWriter.close();
            String result = GameHelp.getGameHelpInfo();
            assertEquals(testData + "\n", result);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
