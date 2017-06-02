/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamequiz;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.ac.aut.ense701.gamemodel.GameHelpTest;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class QuizTest extends junit.framework.TestCase 
{
    private static final String ENCODING = "UTF-8";
    private StringBuffer quizDataStringBuffer;
    
    private Quiz quiz;
    /**
     * Default constructor for the class QuizTest
     */
    public QuizTest() 
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
            quizDataStringBuffer = new StringBuffer();
            quizDataStringBuffer.append(IOUtils.toString(QuizFileReader.class.getResourceAsStream(TextFilePathConstants.QUIZ_DATA), ENCODING));
            //Set Data to null to reset variables and make them read from the file again
            Field quizDataField = QuizFileReader.class.getDeclaredField("quizData");
            quizDataField.setAccessible(true);
            quizDataField.set(null, null);
            Field messageToQuizDataField = QuizFileReader.class.getDeclaredField("messageToQuizData");
            messageToQuizDataField.setAccessible(true);
            messageToQuizDataField.set(null, null);
        } catch (IOException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
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
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), quizDataStringBuffer.toString() , "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(GameHelpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        quiz = null;
    }
    
    @Test
    public void testGetNextQuestionReturnsCorrectQuestion(){
        try {
            String testData = getMockQuizData();
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), testData , "UTF-8");
            QuizFileReader.getQuizData();
            List<String> messages = new ArrayList<String>();
            messages.add("Test Message 1");
            quiz = new Quiz(messages);
            Question question = quiz.getNextQuestion();
            assertEquals("Test Question 1", question.getQuestion());
            assertEquals("option 1", question.getOptions().get(0));
            assertEquals("option 2", question.getOptions().get(1));
            assertEquals("option 3", question.getOptions().get(2));
            assertEquals("option 4", question.getOptions().get(3));
        } catch (IOException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetNextQuestionNoQuestionInStack(){
        try {
            quiz = new Quiz(new ArrayList<String>());
            assertNull(quiz.getNextQuestion());
        } catch (IOException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testIsCorrectAnswerProposedAnswerIsCorrect(){
        try {
            String testData = getMockQuizData();
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), testData , "UTF-8");
            QuizFileReader.getQuizData();
            List<String> messages = new ArrayList<String>();
            messages.add("Test Message 1");
            quiz = new Quiz(messages);
            Question question = quiz.getNextQuestion();
            assertTrue(quiz.isAnswerCorrect(question, 1));
        } catch (IOException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testIsCorrectAnswerProposedAnswerIsIncorrect(){
        try {
            String testData = getMockQuizData();
            FileUtils.writeStringToFile(new File(QuizFileReader.class.getResource(TextFilePathConstants.QUIZ_DATA).getFile()), testData , "UTF-8");
            QuizFileReader.getQuizData();
            List<String> messages = new ArrayList<String>();
            messages.add("Test Message 1");
            quiz = new Quiz(messages);
            Question question = quiz.getNextQuestion();
            assertFalse(quiz.isAnswerCorrect(question, 2));
        } catch (IOException ex) {
            Logger.getLogger(QuizTest.class.getName()).log(Level.SEVERE, null, ex);
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
