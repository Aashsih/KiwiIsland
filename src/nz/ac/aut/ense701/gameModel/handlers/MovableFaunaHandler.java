/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.handlers;

import java.util.ArrayList;
import java.util.List;
import nz.ac.aut.ense701.gameModel.Island;
import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.enums.MoveDirection;
import nz.ac.aut.ense701.gameModel.occupants.Fauna;

/**
 * This class should be extended if any Occupant of type Fauna needs to be made movable on the island
 * 
 * @author aashi
 */
public abstract class MovableFaunaHandler<T extends Fauna> 
{
    protected List<T> fauna;
    protected Island island;
    
    /**
     * Constructs a KiwiHandler by initializing the island and the Fauna list
     * 
     * @param island, the Island on which the movable Fauna is 
     */
    public MovableFaunaHandler(Island island)
    {
        if ( island == null )
        {
            throw new IllegalArgumentException(
                    "Island parameter cannot be null");
        }
        this.island = island;
        fauna = new ArrayList<T>();
    }
    
    /**
     * Adds a fauna to the list of fauna 
     * @param aFauna 
     */
    public void addFauna(T aFauna){
        if ( aFauna == null )
        {
            throw new IllegalArgumentException(
                    "Fauna parameter cannot be null");
        }
        fauna.add(aFauna);
    }
   
    protected abstract boolean moveFauna(Fauna fauna, Position position);
}
