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
import java.util.PriorityQueue;

/**
 *
 * @author aashi
 */
public class DOCMessages {
   
    //stores the static file name that will be read to access facts
    private static final String FILE_LOCATION = "DOCFacts.txt";
   // private static StringBuffer factsBuffer;
    private static PriorityQueue<String> factsQueue ;
    
    /**
     * This method reads the file specified in file location and stores it into
     * the factsBuffer
     */
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
    
    public static PriorityQueue<String> getFacts()
    { 
        readFile();
              
        return factsQueue;

    }
    /**
     * This method returns one fact from the priority queue at a time
     * 
     * @return one fact from the entire queue
    */
    public static String getFact()
    {
        return factsQueue.poll();
    }
}
