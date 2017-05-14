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
public enum Tools {
    SCREW_DRIVER("Screwdriver"), 
    TRAP("Trap");
    
    private static Map<String, Tools> representationToTool = new HashMap<String, Tools>();

    static {
        for(Tools tool : Tools.values()){
            representationToTool.put(tool.representation,tool);
        }
    }

    public static Tools getTool(String representation){
        return representationToTool.get(representation);
    }

    private String representation;

    private Tools(String representation){
        this.representation = representation;
    }

    @Override
    public String toString(){
        return this.representation;
    }
}