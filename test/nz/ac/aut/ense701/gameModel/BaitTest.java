/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.gameModel.occupants.Bait;
import org.junit.Test;

/**
 *
 * @author aashi
 */
public class BaitTest extends junit.framework.TestCase{
    Bait bait;
    Position position;
    Island island;
    
    /**
     * Default constructor for the test class BaitTest
     */
    public BaitTest()
    {
        
    }
    
    /**
     * Sets up the fixture
     * 
     * Called before every test case method
     */
    @Override
    protected void setUp()
    {
        island = new Island(10,10);
        position = new Position(island, 4, 0);
        bait = new Bait(position, "worm", "Can be used to Attract Kiwis", 4, 0);
    }
    
    @Override
    protected void tearDown()
    {
        bait = null;
        island = null;
        position = null;
    }
    
    @Test
    public void testIsUsed()
    {
        assertFalse("Should not be used", bait.isUsed());
        bait.setUsed();
        assertTrue("Shuold be used", bait.isUsed());
    }
    
    @Test
    public void testSetUsed()
    {
        /**
         * This has the same test as the method above
         */
        assertFalse("Shuold not be used", bait.isUsed());
        bait.setUsed();
        assertTrue("Shuold be used", bait.isUsed());
    }
    
    @Test
    public void getStringRepresentation()
    {
        assertEquals("B", bait.getStringRepresentation());
    }
    
}
