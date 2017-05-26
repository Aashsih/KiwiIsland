/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.IOException;
import java.util.Stack;
import nz.ac.aut.ense701.gameQuiz.QuizData;
import nz.ac.aut.ense701.gameQuiz.QuizFileReader;

/**
 *
 * @author aashi
 */
public class DOCMessages {
   
    private static Stack<String> messages;
    
    private DOCMessages(){
        
    }
    
    /**
     * This method returns one fact from the priority queue at a time
     * 
     * @return one fact from the entire queue
     * @throws java.io.IOException
    */
    public static String getFact() throws IOException
    {
        initializeFacts();
        
        if(!messages.isEmpty()){
            
            return messages.pop();
        }
        return null;
        
    }
    
    /**
     * This method gets the QuizData from the QuizData.txt and initializes the
     * message stack
     */
    private static void initializeFacts() throws IOException
    {
        if(messages == null){
            messages = new Stack<String>();
            for(QuizData aQuizData : QuizFileReader.getQuizData())
            {
                messages.push(aQuizData.getMessage());
            }
        }
    }
    
    public static void resetDocMessages(){
        messages = null;
    }
}
