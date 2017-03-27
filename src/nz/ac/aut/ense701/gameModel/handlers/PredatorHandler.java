/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.handlers;

import nz.ac.aut.ense701.gameModel.Island;
import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.enums.MoveDirection;
import nz.ac.aut.ense701.gameModel.occupants.Fauna;

/**
 * This class stores all the predators currently present on the island and is 
 * responsible to alter their population or move them on the island.
 * 
 * @author aashi
 */
public class PredatorHandler extends MovableFaunaHandler{
    
    public PredatorHandler(Island island) {
        super(island);
    }

    @Override
    protected boolean moveFauna(Fauna fauna, Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
