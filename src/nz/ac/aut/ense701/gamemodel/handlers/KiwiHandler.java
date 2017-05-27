/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nz.ac.aut.ense701.gamemodel.Island;
import nz.ac.aut.ense701.gamemodel.Position;
import nz.ac.aut.ense701.gamemodel.occupants.Kiwi;
import nz.ac.aut.ense701.gamemodel.occupants.Occupant;

/**
 * This class stores all the kiwi currently present on the island and is 
 * responsible to alter their population or move them on the island.
 * 
 * @author aashi
 */
public class KiwiHandler extends MovableFaunaHandler{
    //Raidus indicating the number of blocks around the user in which the Attract works when a Bait is used
    private static final int ATTRACT_RADIUS = 2;
    private static final String NO_NEARBY_KIWIS = "No nearby kiwis.";
    private static final String NO_MOVABLE_KIWIS = "The kiwis around you could not move.";
    private static final String BAIT_COULD_ATTRACT_KIWIS = "The Bait worked!! Few kiwi have moved closer to your position";

    private Position playerPosition;
    /**
     * Constructs a KiwiHandler by initializing the island and the Fauna list
     * 
     * @param island, the Island on which the movable Fauna is 
     */
    public KiwiHandler(Island island) {
        super(island);
    }
 
    /**
     * This method is called when the player uses a bait to attract kiwis close to him/her.
     * 
     * @param playerPosition stores the current position of the player
     * @return 
     */
    public String attractKiwis(Position playerPosition){
        if ( playerPosition == null )
        {
            throw new IllegalArgumentException(
                    "Player position cannot be null");
        }
        this.playerPosition = playerPosition;
        String resultMessage;
        List<Kiwi> nearbyKiwis = getKiwisInAttractRadius();
        if(nearbyKiwis == null || nearbyKiwis.isEmpty())
        {
            resultMessage = NO_NEARBY_KIWIS;
        }
        else
        {
            Set<Kiwi> movedKiwis = new HashSet<Kiwi>();
            for(Kiwi kiwi : nearbyKiwis)
            {
                if(moveFauna(kiwi, getPositionToMoveKiwi(kiwi)))
                {
                    movedKiwis.add(kiwi);
                }    
            }
            if(!movedKiwis.isEmpty())
            {
                resultMessage = BAIT_COULD_ATTRACT_KIWIS;
            }
            else
            {
                resultMessage = NO_MOVABLE_KIWIS;
            }
        }
        return resultMessage;
    }
    
    /**
     * This method returns a list of all the kiwis within a radius = ATTRACT_RADIUS around the current player position
     * @param playerPosition, the current position of the player
     * @return list of Kiwi that are within the ATTRACT_RADIUS
     */
    private List<Kiwi> getKiwisInAttractRadius()
    {
        //use the Postion.isPostionValid method to iteratre through all the possible blocks on the island around a radius = ATTRACT_RADIUS
        if(playerPosition == null)
        {
            return null;
        }
        List<Kiwi> nearbyKiwis = new ArrayList<Kiwi>();
        int currentXPosiion = playerPosition.getRow();
        int currentYPosiion = playerPosition.getColumn();
        for(int i = currentXPosiion - ATTRACT_RADIUS; i <= currentXPosiion + ATTRACT_RADIUS; i++)
        {
            for(int j = currentYPosiion - ATTRACT_RADIUS; j <= currentYPosiion + ATTRACT_RADIUS; j++)
            {
                if(Position.isValidPosition(island, i, j) && !(i == currentXPosiion && j == currentYPosiion))
                //if position is valid and is not the same as the current Player position
                {
                    //get occupants for that position on the island
                    Occupant[] occupants = island.getOccupants(new Position(island, i, j));
                    for(Occupant occupant : occupants)
                    {
                        if(occupant instanceof Kiwi)
                        {
                            nearbyKiwis.add((Kiwi) occupant);
                        }
                    }
                }
            }
        }
        return nearbyKiwis;
    }
    
    /**
     * This method is used to find a new position for the kiwi to move.
     * 
     * @param kiwi, kiwi whose new position needs to be calculated
     * @return the position to which the kiwi should be moved
     */
    private Position getPositionToMoveKiwi(Kiwi kiwi)
    {
        if(kiwi == null || playerPosition == null)
        {
            return null;
        }
        Position position;
        if(kiwi.getPosition().getColumn() < playerPosition.getColumn())
        //If kiwi is to the left of the player
        {
            position = new Position(island, kiwi.getPosition().getRow(), kiwi.getPosition().getColumn() + 1);
            if(!island.isOccupantMoveToPositionPossible(kiwi, position))
            {
            } else //if kiwi can move to the right
            {
                return position;
            }
        }
        else if(kiwi.getPosition().getColumn() > playerPosition.getColumn())
        //kiwi is to the right of the player
        {
            position = new Position(island, kiwi.getPosition().getRow(), kiwi.getPosition().getColumn() - 1);
            if(!island.isOccupantMoveToPositionPossible(kiwi, position))
                        {
            } else //if kiwi can move to the left
            {
                return position;
            }
        }
        return findAlternatePosition(kiwi);
    }

    /**
     * 
     * @param kiwi, the kiwi for whom a new position is to be found
     * @return a possible position where the kiwi could be moved.
     *         null, otherwise 
     */
    private Position findAlternatePosition(Kiwi kiwi)
    {
        if(kiwi == null || playerPosition == null)
        {
            return null;
        }
        if(kiwi.getPosition().getRow() < playerPosition.getRow())
        //if kiwi is above the player
        {
            //return a postion that is in a row one below the current kiwi position
            return new Position(island, kiwi.getPosition().getRow() + 1, kiwi.getPosition().getColumn());
        }
        else if(kiwi.getPosition().getRow() > playerPosition.getRow())
        //if kiwi is below the player
        {
            //return a position that is in a row one above the current kiwi position
            return new Position(island, kiwi.getPosition().getRow() - 1, kiwi.getPosition().getColumn());
        }
        return null;
    }
    
//    /**
//     * This method is used to change the position of the fauna passed in the parameter
//     * to the position passed in the parameter
//     * 
//     * @param fauna, the fauna that needs to be moved
//     * @param position, the position to which the fauna needs to be moved
//     * @return 
//     */
//    @Override
//    protected boolean moveFauna(Fauna fauna, Position position) 
//    {
//        if(fauna == null || position == null)
//        {
//            return false;
//        }
//        if(island.isOccupantMoveToPositionPossible(fauna, position))
//        {
//            island.removeOccupant(fauna.getPosition(), fauna);
//            return island.addOccupant(position, fauna);
//        }
//        return false;
//    }

    
}
