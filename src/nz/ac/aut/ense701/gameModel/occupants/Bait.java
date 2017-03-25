/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.occupants;

import nz.ac.aut.ense701.gameModel.Position;

/**
 *
 * @author aashi
 */
public class Bait extends Item{

    public Bait(Position pos, String name, String description, double weight, double size) {
        super(pos, name, description, weight, size);
    }

    @Override
    public String getStringRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
