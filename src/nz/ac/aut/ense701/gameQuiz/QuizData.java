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
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author aashi
 */
public class QuizData {
    private static final String FILE_QUIZ_DATA = "../QuizData";
    private static final String ENCODING = "UTF-8";
    
    private String message;
    private Question question;
    private int answer;
    
    public static final List<QuizData> getQuizDataFromFile() throws IOException{
        Type targetClassType = new TypeToken<ArrayList<QuizData>>() {}.getType();
        return (ArrayList<QuizData>) new Gson().fromJson( FileUtils.readFileToString(new File(FILE_QUIZ_DATA), ENCODING), targetClassType);
    }
    
}
