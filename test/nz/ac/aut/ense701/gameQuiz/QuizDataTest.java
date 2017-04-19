/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameQuiz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.TestCase.assertEquals;
import nz.ac.aut.ense701.gameModel.GameHelp;
import nz.ac.aut.ense701.gameModel.GameHelpTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class QuizDataTest extends junit.framework.TestCase 
{
    private StringBuffer quizDataStringBuffer;

    /**
     * Default constructor for the class QuizDataTest
     */
    public QuizDataTest() {
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
            quizDataStringBuffer.append(FileUtils.readFileToString(new File("QuizData.json"), "UTF-8"));
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
            String testData = "[\n" +
"{\n" +
"	\"message\":\"Test Message 1\",\n" +
"	\"question\":{\n" +
"		\"question\":\"Test Question 1\",\n" +
"		\"options\":[\"option 1\", \"option 2\", \"option 3\", \"option 4\"]	\n" +
"	},\n" +
"	\"answer\":\"1\"\n" +
"}\n]";
            FileUtils.writeStringToFile(new File("QuizData.json"), testData , "UTF-8");
            List<QuizData> quizData = QuizData.getQuizDataFromFile();
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
    
}
