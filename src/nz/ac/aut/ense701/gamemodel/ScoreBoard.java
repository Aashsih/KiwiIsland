/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import nz.ac.aut.ense701.textfiles.TextFilePathConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author aashi
 */
public class ScoreBoard {
    private static final String ENCODING = "UTF-8";
    
    private static List<Score> scoreList;
    
    private ScoreBoard(){
    }
    
    /**
     * Reads the File "PlayerScore.json" if not read already and returns a List of Score
     * @return List of Score list of all the Data required for a quiz
     * @throws IOException 
     */
    public static List<Score> getScoreBoard() throws IOException{
        readFromFile();
        return scoreList;
    }
    
    /**
     * Reads the File "PlayerScore.json" if not read already and stores the data in scoreBoard
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    private static void readFromFile() throws IOException{
        if(scoreList == null){
            Type targetClassType = new TypeToken<ArrayList<Score>>() {}.getType();
            scoreList = (List<Score>) new Gson().fromJson(IOUtils.toString(ScoreBoard.class.getResourceAsStream(TextFilePathConstants.PLAYER_SCORE), ENCODING), targetClassType);
            //scoreList = (List<Score>) new Gson().fromJson( FileUtils.readFileToString(new File(ScoreBoard.class.getResource(TextFilePathConstants.PLAYER_SCORE).getFile()), ENCODING), targetClassType);
        }
    }
    
    /**
     * Write the List of Score to the PlayerScore.json file
     * @param playerScore
     * @throws java.io.IOException
     */
    public static void writeScoreToFile(Score playerScore) throws IOException{
        if(playerScore == null){
            throw new IllegalArgumentException("The parameter \"playerScore\" cannot be null");
        }
        readFromFile();
        if(scoreList == null){
            scoreList = new ArrayList<>();
        }
        scoreList.add(playerScore);
        FileUtils.writeStringToFile(new File(ScoreBoard.class.getResource(TextFilePathConstants.PLAYER_SCORE).getFile()), new Gson().toJson(scoreList) , ENCODING);
        scoreList = null;
    }
}
