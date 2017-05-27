/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nz.ac.aut.ense701.gamemodel.Island;
import nz.ac.aut.ense701.gamemodel.Position;
import nz.ac.aut.ense701.gamemodel.occupants.Predator;

/**
 * This class stores all the predators currently present on the island and is 
 * responsible to alter their population or move them on the island.
 * 
 * @author aashi
 */
public class PredatorHandler extends MovableFaunaHandler{
    private static final int PREDATOR_MOVE_RADIUS = 1;
    
    public PredatorHandler(Island island) {
        super(island);
    }
    
    /**
     * This method checks is there are any available predators on the island and moves them 
     * by one step to the nearest available block in a one block radius.
     * 
     * @param numberOfSteps number of steps taken by the plater
     * @return position to which the predator was moved.
     */
    public Position movePredator(int numberOfSteps){
        if(numberOfSteps < 0){
            throw new IllegalArgumentException(
                    "Number of steps taken cannot be less than 0");
        }
        else if(numberOfSteps == 0){
            return null;
        }
        Position newPredatorPosiion = null;
        if(numberOfSteps % 10 == 0){
            List<Predator> predatorList = new ArrayList<>();
            for(int i = 0; i < island.getNumRows(); i++){
                for(int j = 0; j < island.getNumColumns(); j++){
                    Position aPosition = new Position(island, i, j);
                    if(island.hasPredator(aPosition)){
                        predatorList.add(island.getPredator(aPosition));
                    }
                }
            }
            if(!predatorList.isEmpty()){
                Predator predatorToMove = predatorList.get((new Random()).nextInt(predatorList.size()));
                newPredatorPosiion = movePredatorToAvailablePosition(predatorToMove);
                if(newPredatorPosiion == null){
                    for(Predator aPredator : predatorList){
                        newPredatorPosiion = movePredatorToAvailablePosition(aPredator);
                        if(newPredatorPosiion != null){
                            break;
                        }
                    }
                }
            }
            
        }
        
        return newPredatorPosiion;
    }
    
    /**
     * Checks for all the possible positions where a Predator can be moved
     * in a one block radius and returns the position where the predator was moved to
     * 
     * @param predator predator that needs to be moved
     * @return the position to which the predator has been moved
     */
    private Position movePredatorToAvailablePosition(Predator predator){
        if(predator == null){
            throw new IllegalArgumentException(
                        "Predator passed as a paramter cannot be null");
        }
        Position newPredatorPosition;
        Position predatorPosition = predator.getPosition();
        for(int i = predatorPosition.getRow() - PREDATOR_MOVE_RADIUS; i < predatorPosition.getRow() + PREDATOR_MOVE_RADIUS; i++){
            for(int j = predatorPosition.getColumn() - PREDATOR_MOVE_RADIUS; j < predatorPosition.getColumn() + PREDATOR_MOVE_RADIUS; j++){
                 if(Position.isValidPosition(island, i, j)){
                    newPredatorPosition = new Position(island, i, j);
                    if(moveFauna(predator, newPredatorPosition)){
                        return newPredatorPosition;
                    }
                 }
            }
        }
        return null;
    }
    
}
