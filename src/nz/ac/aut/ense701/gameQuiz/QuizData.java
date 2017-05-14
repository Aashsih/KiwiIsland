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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author aashi
 */
public class QuizData {
        
    private String message;
    private Question question;
    private int answer;
    
    
    //Getters

    public String getMessage() {
        return message;
    }

    public Question getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }
    
}
