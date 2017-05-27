/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import nz.ac.aut.ense701.gamemodel.Position;
import nz.ac.aut.ense701.gamemodel.Island;
import nz.ac.aut.ense701.gamemodel.handlers.KiwiHandler;
import nz.ac.aut.ense701.gamemodel.handlers.PredatorHandler;
import nz.ac.aut.ense701.gamemodel.occupants.Predator;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class PredatorHandlerTest extends junit.framework.TestCase{
    private PredatorHandler predatorHandler;
    private Island island;
    
    /**
     * Default constructor for test class KiwiHandlerTest
     */
    public PredatorHandlerTest()
    {
        
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        island = new Island(10, 10);
        predatorHandler = new PredatorHandler(island);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        island = null;
        predatorHandler = null;
    }
    
    /**
     * This method tests if the predator is moved to the new position returned 
     * by the method under test
     */
    @Test
    public void testMovePredatorPredatorMovePossible(){
        Position predatorPosition = new Position(island, 2, 2);
        island.addOccupant(predatorPosition, new Predator(predatorPosition, "", ""));
        Position newPosition = predatorHandler.movePredator(10);
        assertTrue(island.hasPredator(newPosition));
    }
    
    /**
     * This method tests if a predator is moved when the number of steps passed
     * are not a multiple 10
     */
    @Test
    public void testMovePredatorPositionLessThanTenSteps(){
        Position predatorPosition = new Position(island, 2, 2);
        island.addOccupant(predatorPosition, new Predator(predatorPosition, "", ""));
        assertNull(predatorHandler.movePredator(5));
    }
    
    /**
     * This method tests if movePredator() throws an IllegalArgumentException
     * if the number of steps passed to it are invalid.
     */
    @Test
    public void testMovePredatorIllegalArgumentException()
    {
        try
        {
            predatorHandler.movePredator(-5);
            fail("IllegalArgumentException did not occur");
        }
        catch(Exception e)
        {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
    }
}
