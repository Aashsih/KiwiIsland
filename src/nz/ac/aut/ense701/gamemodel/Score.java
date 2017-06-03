/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;


/**
 *
 * @author aashi
 */
public class Score{
    private String playerName;
    private int playerScore;

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.playerScore = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return playerScore;
    }

    public void setScore(int playerScore) {
        this.playerScore = playerScore;
    }
    
    @Override
    public String toString(){
        return this.getPlayerName() + ":    " + this.getScore();
    }
}
