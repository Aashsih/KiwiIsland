package nz.ac.aut.ense701.gamemodel;

import nz.ac.aut.ense701.gamemodel.Position;
import nz.ac.aut.ense701.gamemodel.Island;
import nz.ac.aut.ense701.gamemodeloccupants.Fauna;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AS
 * @version 2011
 */
public class FaunaTest {
    
    public FaunaTest() {
    }

    /**
     * Test of getStringRepresentation method, of class Fauna.
     */
    @Test
    public void testGetStringRepresentation() {
        Island island = new Island(5,5);
        Position position = new Position(island, 4,4);
        Fauna instance = new Fauna(position, "Oystercatcher", " A nesting oystercatcher");
        String expResult = "F";
        String result = instance.getStringRepresentation();
        assertEquals(expResult, result);
    }
    
}
