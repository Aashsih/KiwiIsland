/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.handlers;

import nz.ac.aut.ense701.gameModel.Island;
import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.occupants.Fauna;

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
    
    protected abstract boolean moveFauna(Fauna fauna, Position position);
    

}
