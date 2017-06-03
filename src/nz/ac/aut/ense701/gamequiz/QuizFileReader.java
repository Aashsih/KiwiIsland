/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamequiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author aashi
 */
public class QuizFileReader {
    
    private static final String ENCODING = "UTF-8";

    private static List<QuizData> quizData;
    private static Map<String, QuizData> messageToQuizData;
    
    private QuizFileReader(){
        
    }
    
    /**
     * Reads the File "QuizData.json" if not read already and returns a List of QuizData
     * @return List of QuizData list of all the Data required for a quiz
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static final List<QuizData> getQuizData() throws IOException
    {
        readQuizDataFromFile();
        return Collections.unmodifiableList(quizData);
    }
    
    /**
     * Returns the QuizData corresponding to the message passed in the parameter
     * @param message contains the message for which the QuizData is required
     * @return QuizData corresponding to the message passed as a parameter
     * @throws java.io.IOException
     */
    public static final QuizData getQuizDataForMessage(String message) throws IOException{
        if(messageToQuizData == null){
            readQuizDataFromFile();
        }
        return messageToQuizData.get(message);
    }
    
    /**
     * resets the data that has been read from the file
     */
    public static void resetDataReadFromFile(){
        quizData = null;
        messageToQuizData = null;
    }
    
    /**
     * Reads the File "QuizData.txt" if not read already and stores the data in quizData and messageToQuizData
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    private static void readQuizDataFromFile() throws IOException{
        if(quizData == null){
            Type targetClassType = new TypeToken<ArrayList<QuizData>>() {}.getType();
            quizData = (List<QuizData>) new Gson().fromJson(IOUtils.toString(QuizFileReader.class.getResourceAsStream(TextFilePathConstants.QUIZ_DATA), ENCODING), targetClassType);
            messageToQuizData = new HashMap<>();
            for(QuizData aQuizQuestion : quizData){
                messageToQuizData.put(aQuizQuestion.getMessage(), aQuizQuestion);
            }
        }
    }
}
