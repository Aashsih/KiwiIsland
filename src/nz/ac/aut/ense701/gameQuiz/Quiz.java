/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameQuiz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.aut.ense701.gameModel.Player;

/**
 *
 * @author aashi
 */
public class Quiz {
    private Map<Question, Integer> questionToAnswer;
    
    public Quiz(List<String> playerMesages) throws IOException{
        if(playerMesages == null){
            throw new IllegalArgumentException();
        }
        prepareQuiz();
    }
    
    private void prepareQuiz() throws IOException{
        questionToAnswer = new HashMap<Question, Integer>();
        List<QuizData> quizData = QuizFileReader.getQuizData();
        
    }
    
}
