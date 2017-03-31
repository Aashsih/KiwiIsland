/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nz.ac.aut.ense701.gameModel.Island;
import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.enums.MoveDirection;
import nz.ac.aut.ense701.gameModel.occupants.Fauna;
import nz.ac.aut.ense701.gameModel.occupants.Kiwi;
import nz.ac.aut.ense701.gameModel.occupants.Occupant;
import nz.ac.aut.ense701.gameModel.occupants.Predator;

/**
 * This class should be extended if any Occupant of type Fauna needs to be made movable on the island
 * 
 * @author aashi
 */
public abstract class MovableFaunaHandler
{
    protected Set<Fauna> faunaList;
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
        faunaList = new HashSet<Fauna>();
    }
    
    /**
     * This method is used to prepare the list of all the kiwis present on the island
     * by iterating through 
     */
    protected void createFaunaList()
    {
        for(int i = 0; i < island.getNumRows(); i++)
        //for all the rows on the island
        {
            for(int j = 0; j < island.getNumColumns(); j++)
            //for all the columns of the island
            {
                Occupant[] occupants = island.getOccupants(new Position(island, i, j));
                for(Occupant occupant : occupants)
                //for all the occupants on a position on the island
                {
                    if(occupant instanceof Fauna)
                    {
                        if(occupant instanceof Kiwi && this instanceof KiwiHandler)
                        {
                            faunaList.add((Fauna) occupant);
                        }
                        else if(occupant instanceof Predator && this instanceof PredatorHandler)
                        {
                            faunaList.add((Fauna) occupant);
                        }
                    }
                }
            }
        }
    }
    
    protected abstract boolean moveFauna(Fauna fauna, Position position);
}
