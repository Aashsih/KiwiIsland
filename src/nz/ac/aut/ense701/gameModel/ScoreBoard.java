/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nz.ac.aut.ense701.gameQuiz.QuizData;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author aashi
 */
public class ScoreBoard {
    private static final String SCORE_BOARD_FILE_NAME = "PlayerScore.json";
    private static final String ENCODING = "UTF-8";
    
    private static List<Score> scoreBoard;
    
    public static List<Score> getScoreBoard() throws IOException{
        readFromFile();
        return scoreBoard;
    }
    
    public static void readFromFile() throws IOException{
        if(scoreBoard == null){
            Type targetClassType = new TypeToken<ArrayList<Score>>() {}.getType();
            scoreBoard = (List<Score>) new Gson().fromJson( FileUtils.readFileToString(new File(SCORE_BOARD_FILE_NAME), ENCODING), targetClassType);
        }
    }
}
