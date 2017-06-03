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
import nz.ac.aut.ense701.gamemodel.occupants.Fauna;
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
    public Position movePredatorToRandomPosition(int numberOfSteps){
        if(numberOfSteps < 0){
            throw new IllegalArgumentException(
                    "Number of steps taken cannot be less than 0");
        }
        else if(numberOfSteps == 0){
            return null;
        }
        Position newPredatorPosiion = null;
        if(numberOfSteps % 10 == 0){
            List<Predator> predatorList = getAllPredatorsOnIsland();
            if(!predatorList.isEmpty()){
                Predator predatorToMove = predatorList.get((new Random()).nextInt(predatorList.size()));
                newPredatorPosiion = moveAPredatorToAvailablePosition(predatorToMove);
                if(newPredatorPosiion == null){
                    movePredatorToAvailablePosition(predatorList);
                }
            }
            
        }
        
        return newPredatorPosiion;
    }
    
    /**
     * Moves one predator from the provided list to an available position
     * @param predatorList
     * @param newPredatorPosiion 
     */
    private void movePredatorToAvailablePosition(List<Predator> predatorList){
        Position newPredatorPosiion;
        for(Predator aPredator : predatorList){
            newPredatorPosiion = moveAPredatorToAvailablePosition(aPredator);
            if(newPredatorPosiion != null){
                break;
            }
        }
    }
    
    /**
     * This method is used to get a list of predators on the island
     * @return list of predators on the island
     */
    private List<Predator> getAllPredatorsOnIsland(){
        List<Predator> predatorList = new ArrayList<>();
        for(int i = 0; i < island.getNumRows(); i++){
            for(int j = 0; j < island.getNumColumns(); j++){
                Position aPosition = new Position(island, i, j);
                if(island.hasPredator(aPosition)){
                    predatorList.add(island.getPredator(aPosition));
                }
            }
        }
        return predatorList;
    }
    
    /**
     * Checks for all the possible positions where a Predator can be moved
     * in a one block radius and returns the position where the predator was moved to
     * 
     * @param predator predator that needs to be moved
     * @return the position to which the predator has been moved
     */
    private Position moveAPredatorToAvailablePosition(Predator predator){
        if(predator == null){
            throw new IllegalArgumentException(
                        "Predator passed as a paramter cannot be null");
        }
        Position predatorPosition = predator.getPosition();
        for(int i = predatorPosition.getRow() - PREDATOR_MOVE_RADIUS; i < predatorPosition.getRow() + PREDATOR_MOVE_RADIUS; i++){
            for(int j = predatorPosition.getColumn() - PREDATOR_MOVE_RADIUS; j < predatorPosition.getColumn() + PREDATOR_MOVE_RADIUS; j++){
                return  moveFaunaToPosition(predator, i, j);
            }
        }
        return null;
    }
   
    /**
     * Moves the Fauna passed as a parameter to the specified row and column on the island
     * @param fauna fauna to be moved
     * @param row 
     * @param column
     * @return Position of where the fauna was moved
     */
    private Position moveFaunaToPosition(Fauna fauna, int row, int column){
        if(Position.isValidPosition(island, row, column)){
            Position newPredatorPosition = new Position(island, row, column);
            if(moveFauna(fauna, newPredatorPosition)){
                return newPredatorPosition;
            }
         }
        return null;
    }
}
