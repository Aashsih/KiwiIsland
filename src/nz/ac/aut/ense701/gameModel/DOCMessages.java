/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import nz.ac.aut.ense701.gameQuiz.QuizData;
import nz.ac.aut.ense701.gameQuiz.QuizFileReader;

/**
 *
 * @author aashi
 */
public class DOCMessages {
   
    //stores the static file name that will be read to access facts
    @Deprecated private static final String FILE_LOCATION = "DOCFacts.txt";
   
    private static Stack<String> messages;
    @Deprecated private static PriorityQueue<String> factsQueue ;
    
    /**
     * This method returns one fact from the priority queue at a time
     * 
     * @return one fact from the entire queue
    */
    public static String getFact() throws IOException
    {
        initializeFacts();
        return messages.pop();
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
    
    /**
     * This method reads the file specified in file location and stores it into
     * the factsBuffer
     */
    @Deprecated
    private static void readFile()
    {
        factsQueue = new PriorityQueue<String> ();      

        try{
            BufferedReader factsBufferedReader = new BufferedReader(new FileReader(FILE_LOCATION));
            String line;
            while((line = factsBufferedReader.readLine())!= null)
            {

                factsQueue.add(line);
            }
            factsBufferedReader.close();
        }
        catch(FileNotFoundException e)
        {

        } catch (IOException ex) {

        }  
    }

    
    /**
     * This method reads the file and gets the string representation of the 
     * facts stored in file specified in the file location.
     * 
     * @return string representation of the facts used to spread DOC awareness 
     */
    @Deprecated
    private static PriorityQueue<String> getFacts()
    { 
        readFile();
              
        return factsQueue;

    }
    
}
