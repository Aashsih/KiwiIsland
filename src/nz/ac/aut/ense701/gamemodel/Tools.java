/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aashi
 */
@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public enum Tools {
    SCREW_DRIVER("Screwdriver"), 
    TRAP("Trap");
    
    private static final Map<String, Tools> representationToTool = new HashMap<>();

    static {
        for(Tools tool : Tools.values()){
            representationToTool.put(tool.representation,tool);
        }
    }

    private String representation;

    private Tools(String representation){
        this.representation = representation;
    }

    public static Tools getTool(String representation){
        return representationToTool.get(representation);
    }


    @Override
    public String toString(){
        return this.representation;
    }
}
