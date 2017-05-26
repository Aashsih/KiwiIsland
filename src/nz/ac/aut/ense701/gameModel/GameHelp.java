/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author aashi
 */
public class GameHelp 
{
    //Mostly contains static methods - Used in KiwiCountUI
    private static final String FILE_LOCATION = "Help.txt";
    private static final String ENCODING = "UTF-8";
    private static StringBuilder gameHelpStringBuffer;

    /**
     * Reads the Help File in text mode and stores it in the String Buffer for later retrieval
     * 
     * @throws IOException 
     */
    private static void readGameHelpTextFromFile() throws IOException
    {
       gameHelpStringBuffer = new StringBuilder();
       gameHelpStringBuffer.append(FileUtils.readFileToString(new File(FILE_LOCATION), ENCODING));
    }
    
    /**
     * This method is used to get string representation of the Help for KiwiIsland Game
     * as read from the text file specified at the FILE_LOCATION
     * 
     * @return string representation of the Help for KiwiIsland Game
     */
    public static String getGameHelpInfo() throws IOException
    {
        if(gameHelpStringBuffer == null){
            readGameHelpTextFromFile();
        }
        return gameHelpStringBuffer.toString();
    }
    
}
