/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.game_model;

import nz.ac.aut.ense701.game_model.Position;
import nz.ac.aut.ense701.game_model.Island;
import java.util.ArrayList;
import java.util.List;
import nz.ac.aut.ense701.game_model.handlers.KiwiHandler;
import nz.ac.aut.ense701.game_model.occupants.Fauna;
import nz.ac.aut.ense701.game_model.occupants.Hazard;
import nz.ac.aut.ense701.game_model.occupants.Kiwi;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author aashi
 */
public class KiwiHandlerTest extends junit.framework.TestCase{
    private KiwiHandler kiwiHandler;
    private Position playerPosition;
    private Island island;
    
    /**
     * Default constructor for test class KiwiHandlerTest
     */
    public KiwiHandlerTest()
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
        kiwiHandler = new KiwiHandler(island);
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
        kiwiHandler = null;
    }
    
    @Test
    public void testAtrractKiwisIllegalArgumentException()
    {
        try
        {
            kiwiHandler.attractKiwis(null);
            fail("IllegalArgumentException did not occur");
        }
        catch(Exception e)
        {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
    }
    
    @Test
    public void testAtrractKiwisNoKiwisOnIsland()
    {
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("No nearby kiwis.", result);
    }
    
    @Test
    public void testAtrractKiwisOutsideAttractRadius()
    {
        Position position = new Position(island, 2, 2); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("No nearby kiwis.", result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToHazards()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Hazard(position,"Hazard", "Test Hazard", 1));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Hazard(position,"Hazard", "Test Hazard", 1));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("The kiwis around you could not move.", result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToNeighbouringKiwis()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        position = new Position(island, 5, 5); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("The kiwis around you could not move.", result);
    }
    
    @Test
    public void testAtrractKiwisNoMovableKiwisDueToGridSqaureCapacity()
    {
        Position position = new Position(island, 4, 4); 
        island.addOccupant(position, new Kiwi(position,"Kiwi", "Test Kiwi"));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna1", "Test Fauna"));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna2", "Test Fauna"));
        position = new Position(island, 4, 5); 
        island.addOccupant(position, new Fauna(position,"Fauna3", "Test Fauna"));
        
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna1", "Test Fauna"));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna2", "Test Fauna"));
        position = new Position(island, 5, 4); 
        island.addOccupant(position, new Fauna(position,"Fauna3", "Test Fauna"));
        String result = kiwiHandler.attractKiwis(new Position(island, 5,5));
        assertEquals("The kiwis around you could not move.", result);
    }
}
