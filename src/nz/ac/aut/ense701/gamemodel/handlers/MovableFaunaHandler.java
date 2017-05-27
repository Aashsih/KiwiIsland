/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel.handlers;

import nz.ac.aut.ense701.gamemodel.Island;
import nz.ac.aut.ense701.gamemodel.Position;
import nz.ac.aut.ense701.gamemodel.occupants.Fauna;

/**
 * This class should be extended if any Occupant of type Fauna needs to be made movable on the island
 * 
 * @author aashi
 */
public abstract class MovableFaunaHandler
{
    //protected Set<Fauna> faunaList;
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
        //faunaList = new HashSet<Fauna>();
    }
    
    /**
     * This method is used to change the position of the fauna passed in the parameter
     * to the position passed in the parameter
     * 
     * @param fauna, the fauna that needs to be moved
     * @param position, the position to which the fauna needs to be moved
     * @return 
     */
    protected boolean moveFauna(Fauna fauna, Position position){
        if(fauna == null || position == null)
        {
            return false;
        }
        if(island.isOccupantMoveToPositionPossible(fauna, position))
        {
            island.removeOccupant(fauna.getPosition(), fauna);
            return island.addOccupant(position, fauna);
        }
        return false;
    }
    

}
