/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamequiz;

import nz.ac.aut.ense701.gamequiz.QuizFileReader;
import nz.ac.aut.ense701.gamequiz.QuizData;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.TestCase.assertEquals;
import nz.ac.aut.ense701.gamemodel.GameHelp;
import nz.ac.aut.ense701.gamemodel.GameHelpTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 *
 * @author aashi
 */
public class QuizFileReaderTest extends junit.framework.TestCase 
{
    private StringBuffer quizDataStringBuffer;

    /**
     * Default constructor for the class QuizDataTest
     */
    public QuizFileReaderTest() {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp() throws IllegalAccessException
    {
        try {
            quizDataStringBuffer = new StringBuffer();
            quizDataStringBuffer.append(FileUtils.readFileToString(new File("QuizData.json"), "UTF-8"));
            //Set Data to null to reset variables and make them read from the file again
            Field quizDataField = QuizFileReader.class.getDeclaredField("quizData");
            quizDataField.setAccessible(true);
            quizDataField.set(null, null);
            Field messageToQuizDataField = QuizFileReader.class.getDeclaredField("messageToQuizData");
            messageToQuizDataField.setAccessible(true);
            messageToQuizDataField.set(null, null);
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(QuizFileReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(QuizFileReaderTest.class.getName()).log(Level.SEVERE, null, ex);
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
            FileUtils.writeStringToFile(new File("QuizData.json"), quizDataStringBuffer.toString() , "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetQuizDataFromFileReturnsListOfQuizData()
    {
        try 
        {
            String testData = getMockQuizData();
            FileUtils.writeStringToFile(new File("QuizData.json"), testData , "UTF-8");
            List<QuizData> quizData = QuizFileReader.getQuizData();
            assertEquals("Test Message 1", quizData.get(0).getMessage());
            assertEquals("Test Question 1", quizData.get(0).getQuestion().getQuestion());
            assertEquals("option 1", quizData.get(0).getQuestion().getOptions().get(0));
            assertEquals("option 2", quizData.get(0).getQuestion().getOptions().get(1));
            assertEquals("option 3", quizData.get(0).getQuestion().getOptions().get(2));
            assertEquals("option 4", quizData.get(0).getQuestion().getOptions().get(3));
            assertEquals(1, quizData.get(0).getAnswer());
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetQuizDataForMessage(){
        String testData = getMockQuizData();
        try {
            FileUtils.writeStringToFile(new File("QuizData.json"), testData , "UTF-8");
            QuizFileReader.getQuizData();
            QuizData quizData = QuizFileReader.getQuizDataForMessage("Test Message 1");
            assertEquals("Test Message 1", quizData.getMessage());
            assertEquals("Test Question 1", quizData.getQuestion().getQuestion());
            assertEquals("option 1", quizData.getQuestion().getOptions().get(0));
            assertEquals("option 2", quizData.getQuestion().getOptions().get(1));
            assertEquals("option 3", quizData.getQuestion().getOptions().get(2));
            assertEquals("option 4", quizData.getQuestion().getOptions().get(3));
            assertEquals(1, quizData.getAnswer());
        } catch (IOException ex) {
            Logger.getLogger(QuizFileReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    private String getMockQuizData(){
        return "[\n" +
"{\n" +
"	\"message\":\"Test Message 1\",\n" +
"	\"question\":{\n" +
"		\"question\":\"Test Question 1\",\n" +
"		\"options\":[\"option 1\", \"option 2\", \"option 3\", \"option 4\"]	\n" +
"	},\n" +
"	\"answer\":\"1\"\n" +
"}\n]";
    }
}
