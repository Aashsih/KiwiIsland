package nz.ac.aut.ense701.gamemodel;

import nz.ac.aut.ense701.gamemodel.occupants.Kiwi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AS
 * @version 2011
 */
public class KiwiTest {
    
    private Kiwi kiwi;
    private Position position;
    private Island island;
    
    public KiwiTest() {
        //Default constructor for the test class
    }
    
    @Before
    public void setUp() {
        island = new Island(5,5);
        position = new Position(island, 4,4);
        kiwi = new Kiwi(position, "Kiwi", "A little spotted kiwi");   
    }
    
    @After
    public void tearDown() {
        island = null;
        position = null;
        kiwi = null;
    }

    @Test
    public void testCountedNotCounted() {
        assertFalse("Should not be counted", kiwi.counted());
    }
    
    @Test
    public void testCountedIsCounted() {
        assertFalse("Should not be counted", kiwi.counted());
        kiwi.count();
        assertTrue("Should  be counted", kiwi.counted());
    }

    /**
     * Test of getStringRepresentation method, of class Kiwi.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("K", kiwi.getStringRepresentation());
    }
    
}
