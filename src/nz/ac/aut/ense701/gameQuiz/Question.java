/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameQuiz;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author aashi
 */
public class Question {
    private String question;
    private List<String> options;

    //Getters
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options);
    }
    
}
