/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamequiz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 *
 * @author aashi
 */
public class Quiz {
    private Map<Question, Integer> questionToAnswer;
    private Stack<Question> questionStack;
    private int answerOfQuestion;
    
    /**
     * Constructs an object of type Quiz by preparing a map of question to answers based on the messages
     * collected by the player.
     * It also stores all the questions in a Stack which can be used to get the next question while playing
     * the Quiz
     * @param playerMessages list of messages that the player has collected
     * @throws IOException 
     */
    public Quiz(List<String> playerMessages) throws IOException{
        if(playerMessages == null){
            throw new IllegalArgumentException();
        }
        prepareQuiz(playerMessages);
    }

    /**
     * Gets the next question from the questionStack for the Quiz
     * @return next question in the Quiz
     */
    public Question getNextQuestion(){
        if(questionStack == null || questionStack.isEmpty()){
            return null;
        }
        else{
     
            answerOfQuestion = questionToAnswer.get(questionStack.peek());
            return questionStack.pop();
        }
    }

    /**
     * Checks if the answer passed in the parameter is the correct answer for the question passed in the parameter
     * @param question A Question in the Quiz
     * @param proposedAnswer proposed answer for the question passed in the parameter
     * @return true, if the proposed answer matches the correct answer for the question passed in the parameter
     *         false, otherwise
     */
    public boolean isAnswerCorrect(Question question, int proposedAnswer){
        if(question == null || proposedAnswer <= 0){
            return false;
        }
        if(questionToAnswer.containsKey(question)){
            int correctAnswer = questionToAnswer.get(question);
            if(proposedAnswer == correctAnswer){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Prepares a map of question to answers based on the messages
     * collected by the player.
     * It also stores all the questions in a Stack which can be used to get the next question while playing
     * the Quiz
     * @param playerMessages list of messages that the player has collected
     * @throws IOException 
     */
    private void prepareQuiz(List<String> playerMessages) throws IOException{
        questionToAnswer = new HashMap<Question, Integer>();
        questionStack = new Stack<Question>();
        for(String message : playerMessages){
            QuizData quizData = QuizFileReader.getQuizDataForMessage(message);
            questionToAnswer.put(quizData.getQuestion(), quizData.getAnswer());
            questionStack.push(quizData.getQuestion());
        }      
    }
    /**
     * Displays a dialogue box to show the correct answer of the current question
     * @return answer to the question as an integer
     */
    public int correctAnswer(){

        return this.answerOfQuestion;
        
    }
}
