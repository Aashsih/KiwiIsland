/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.util.List;

/**
 *
 * @author aashi
 */
public class ScoreBoard {
    private static final String SCORE_BOARD_FILE_NAME = "PlayerScore.json";
    private static List<Score> scoreBoard;
    
    public static List<Score> getScoreBoard(){
        if(scoreBoard == null){
        }
        return scoreBoard;
    }
    
    public void readFromFile(){
        
    }
}
