/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aashi
 */
public enum Occupants {
    FAUNA("F"),
    FOOD("E"),
    HAZARD("H"),
    KIWI("K"),
    PREDATOR("P"), 
    TOOL("T"),
    BAIT("B");
    
    private static Map<String, Occupants> representationToOccupant = new HashMap<String, Occupants>();

    static {
        for(Occupants occupant : Occupants.values()){
            representationToOccupant.put(occupant.representation,occupant);
        }
    }

    public static Occupants getOccupant(String representation){
        return representationToOccupant.get(representation);
    }

    private String representation;

    private Occupants(String representation){
        this.representation = representation;
    }

    @Override
    public String toString(){
        return this.representation;
    }
    
}
