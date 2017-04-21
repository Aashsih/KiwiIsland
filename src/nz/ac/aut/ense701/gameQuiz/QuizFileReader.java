/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameQuiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author aashi
 */
public class QuizFileReader {
    private static final String FILE_QUIZ_DATA = "QuizData.json";
    private static final String ENCODING = "UTF-8";

    private static List<QuizData> quizData;
    private static Map<String, QuizData> messageToQuizData;
    
    /**
     * Reads the File "QuizData.txt" and returns a List<QuizData>
     * @return List<QuizData>
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static final List<QuizData> getQuizData() throws IOException
    {
        if(quizData == null){
            Type targetClassType = new TypeToken<ArrayList<QuizData>>() {}.getType();
            quizData = (List<QuizData>) new Gson().fromJson( FileUtils.readFileToString(new File(FILE_QUIZ_DATA), ENCODING), targetClassType);
            messageToQuizData = new HashMap<String, QuizData>();
            for(QuizData aQuizQuestion : quizData){
                messageToQuizData.put(aQuizQuestion.getMessage(), aQuizQuestion);
            }
        }
        return Collections.unmodifiableList(quizData);
    }
    
    /**
     * Returns the QuizData corresponding to the message passed in the parameter
     * @param message contains the message for which the QuizData is required
     * @return QuizData corresponding to the message passed as a parameter
     */
    public static final QuizData getQuizDataForMessage(String message){
        if(messageToQuizData == null){
            return null;
        }
        return messageToQuizData.get(message);
    }
}
