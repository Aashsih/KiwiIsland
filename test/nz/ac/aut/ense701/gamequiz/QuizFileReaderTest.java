/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamequiz;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.ac.aut.ense701.gamemodel.GameHelpTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author aashi
 */
public class QuizFileReaderTest extends junit.framework.TestCase 
{
    private static final String TEST_MESSAGE = "Test Message 1";
    private static final String ENCODING = "UTF-8";
    private StringBuilder quizDataStringBuffer;

    /**
     * Default constructor for the class QuizDataTest
     */
    public QuizFileReaderTest() {
        //Default constructor for the test class
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
            quizDataStringBuffer = new StringBuilder();
            quizDataStringBuffer.append(IOUtils.toString(QuizFileReader.class.getResourceAsStream(TextFilePathConstants.QUIZ_DATA), ENCODING));
            //Set Data to null to reset variables and make them read from the file again
            Field quizDataField = QuizFileReader.class.getDeclaredField("quizData");
            quizDataField.setAccessible(true);
            quizDataField.set(null, null);
            Field messageToQuizDataField = QuizFileReader.class.getDeclaredField("messageToQuizData");
            messageToQuizDataField.setAccessible(true);
            messageToQuizDataField.set(null, null);
        } catch (NoSuchFieldException | SecurityException | IOException ex) {
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
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), quizDataStringBuffer.toString() , ENCODING);
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
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), testData , ENCODING);
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
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), testData , ENCODING);
            QuizFileReader.getQuizData();
            QuizData quizData = QuizFileReader.getQuizDataForMessage(TEST_MESSAGE);
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
